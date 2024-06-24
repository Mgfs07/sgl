import {Component, OnInit} from '@angular/core';
import {Column} from "../../shared/models/colum.model";
import {DialogService, DynamicDialogRef} from "primeng/dynamicdialog";
import {ConfirmationService, MessageService} from "primeng/api";
import {CoordenadorService} from "../../shared/services/coordenador.service";
import {CoordenadorFormComponent} from "./coordenador-form/coordenador-form.component";

@Component({
  selector: 'app-coordenador',
  templateUrl: './coordenador.component.html',
  styleUrls: ['./coordenador.component.scss']
})
export class CoordenadorComponent implements OnInit {

    cols!: Column[];
    coordenadores: any;
    ref: DynamicDialogRef | undefined;
    display: boolean = false;

    constructor(public dialogService: DialogService,
                private messageService: MessageService,
                private coordenadorService: CoordenadorService,
                private confirmationService: ConfirmationService) {
    }

    ngOnInit() {
        this.construirColunasListagem();
        this.buscarCoordenadores()
    }

    private construirColunasListagem() {
        this.cols = [
            {field: 'nome', header: 'Nome', text: true},
            {field: 'acoes', header: 'Ações'}
        ];
    }

    private buscarCoordenadores() {
        this.coordenadorService.findAll().subscribe((value: any) => {
            this.coordenadores = value;
        })
    }

    novoCoordenador() {
        this.ref = this.dialogService.open(CoordenadorFormComponent,
            {
                header: 'Novo Coordenador',
                width: '35%',
                data: {acao: ''}
            });
        this.ref.onClose.subscribe((coordenador) => {
            if (coordenador) {
                this.messageService.add({
                    severity: 'success',
                    summary: 'Success',
                    detail: 'O Coordenador ' + coordenador.nome + ' foi cadastrado com sucesso!'
                })
                this.buscarCoordenadores();
            }
        });
    }

    handleAcao(rowData: any, acao: string) {
        this.coordenadorService.findById(rowData.id).subscribe((value) => {
            this.ref = this.dialogService.open(CoordenadorFormComponent,
                {
                    header: 'Formulário Coordenador',
                    width: '35%',
                    data: {coordenador: value, acao: acao}
                });
            this.ref.onClose.subscribe((coordenador) => {
                if (coordenador) {
                    this.messageService.add({
                        severity: 'success',
                        summary: 'Success',
                        detail: 'O coordenador ' + coordenador.nome + ' foi editado com sucesso!'
                    })
                    this.buscarCoordenadores()
                }
            });
        })
    }

    excluirCoordenadoria(matricula) {
        this.confirmationService.confirm({
            message: 'Tem certeza que deseja excluir o registro?',
            header: 'Confirmação de Exclusão',
            icon: 'pi pi-info-circle',
            acceptLabel: 'Sim',
            rejectLabel: 'Cancelar',
            accept: () => {
                this.coordenadorService.deletePorMatricula(matricula).subscribe(() => {
                    this.buscarCoordenadores();
                })
                this.messageService.add({severity: 'info', summary: 'Confirmação', detail: 'Coordenador inativado!'});
            }
        });
    }

}
