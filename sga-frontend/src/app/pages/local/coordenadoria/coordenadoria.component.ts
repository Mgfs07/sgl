import {Component, OnInit} from '@angular/core';
import {Column} from "../../../shared/models/colum.model";
import {DialogService, DynamicDialogRef} from "primeng/dynamicdialog";
import {ConfirmationService, MessageService} from "primeng/api";
import {CoordenadoriaService} from "../../../shared/services/coordenadoria.service";
import {CoordenadoriaFormComponent} from "./coordenadoria-form/coordenadoria-form.component";

@Component({
    selector: 'app-coordenadoria',
    templateUrl: './coordenadoria.component.html',
    styleUrls: ['./coordenadoria.component.scss'],
    providers: [DialogService, ConfirmationService, MessageService]

})
export class CoordenadoriaComponent implements OnInit {
    cols!: Column[];
    coordenadorias: any;
    ref: DynamicDialogRef | undefined;
    display: boolean = false;


    constructor(public dialogService: DialogService,
                private messageService: MessageService,
                private coordenadoriaService: CoordenadoriaService,
                private confirmationService: ConfirmationService) {
    }

    ngOnInit() {
        this.construirColunasListagem();
        this.buscarCoordenadorias()
    }

    private construirColunasListagem() {
        this.cols = [
            {field: 'nome', header: 'Nome', text: true},
            {field: 'acoes', header: 'Ações'}
        ];
    }

    private buscarCoordenadorias() {
        this.coordenadoriaService.findAll().subscribe((value: any) => {
            this.coordenadorias = value;
        })
    }

    novaCoordenadoria() {
        this.ref = this.dialogService.open(CoordenadoriaFormComponent,
            {
                header: 'Nova Coordenadoria',
                width: '35%',
                data: {acao: ''}
            });
        this.ref.onClose.subscribe((coordenadoria) => {
            if (coordenadoria) {
                this.messageService.add({
                    severity: 'success',
                    summary: 'Success',
                    detail: 'A Coordenadoria ' + coordenadoria.nome + ' foi cadastrada com sucesso!'
                })
                this.buscarCoordenadorias();
            }
        });
    }

    handleAcao(rowData: any, acao: string) {
        this.coordenadoriaService.findById(rowData.id).subscribe((value) => {
            this.ref = this.dialogService.open(CoordenadoriaFormComponent,
                {
                    header: 'Formulário Coordenadoria',
                    width: '35%',
                    data: {coordenadoria: value, acao: acao}
                });
            this.ref.onClose.subscribe((coordenadoria) => {
                if (coordenadoria) {
                    this.messageService.add({
                        severity: 'success',
                        summary: 'Success',
                        detail: 'A coordenadoria ' + coordenadoria.nome + ' foi editada com sucesso!'
                    })
                    this.buscarCoordenadorias()
                }
            });
        })
    }

    excluirCoordenadoria(id) {
        this.confirmationService.confirm({
            message: 'Tem certeza que deseja excluir o registro?',
            header: 'Confirmação de Exclusão',
            icon: 'pi pi-info-circle',
            acceptLabel: 'Sim',
            rejectLabel: 'Cancelar',
            accept: () => {
                this.coordenadoriaService.delete(id).subscribe(() => {
                    this.buscarCoordenadorias();
                })
                this.messageService.add({severity: 'info', summary: 'Confirmação', detail: 'Coordenadoria inativada!'});
            }
        });
    }

}
