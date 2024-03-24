import {Component, OnInit} from '@angular/core';
import {Column} from "../../../shared/models/colum.model";
import {DialogService, DynamicDialogRef} from "primeng/dynamicdialog";
import {ConfirmationService, MessageService} from "primeng/api";
import {EquipamentoFormComponent} from "./equipamento-form/equipamento-form.component";
import {EquipamentoService} from "../../../shared/services/equipamento.service";

@Component({
    selector: 'app-equipamento',
    templateUrl: './equipamento.component.html',
    styleUrls: ['./equipamento.component.scss'],
    providers: [DialogService, ConfirmationService, MessageService]
})
export class EquipamentoComponent implements OnInit {
    cols!: Column[];
    equipamentos: any;
    ref: DynamicDialogRef | undefined;
    display: boolean = false;


    constructor(public dialogService: DialogService,
                private messageService: MessageService,
                private equipamentoService: EquipamentoService,
                private confirmationService: ConfirmationService) {
    }

    ngOnInit() {
        this.construirColunasListagem();
        this.buscarEquipamentos()
    }

    private construirColunasListagem() {
        this.cols = [
            {field: 'nome', header: 'Nome', text: true},
            {field: 'acoes', header: 'Ações'}
        ];
    }

    private buscarEquipamentos() {
        this.equipamentoService.findAll().subscribe((value: any) => {
            this.equipamentos = value;
        })
    }

    novoEquipamento() {
        this.ref = this.dialogService.open(EquipamentoFormComponent,
            {
                header: 'Novo Equipamento',
                width: '35%',
                data: {acao: ''}
            });
        this.ref.onClose.subscribe((equipamento) => {
            if (equipamento) {
                this.messageService.add({
                    severity: 'success',
                    summary: 'Success',
                    detail: 'O equipamento ' + equipamento.nome + ' foi cadastrado com sucesso!'
                })
                this.buscarEquipamentos();
            }
        });
    }

    handleAcao(rowData: any, acao: string) {
        this.equipamentoService.findById(rowData.id).subscribe((value) => {
            this.ref = this.dialogService.open(EquipamentoFormComponent,
                {
                    header: 'Formulário Equipamento',
                    width: '35%',
                    data: {equipamento: value, acao: acao}
                });
            this.ref.onClose.subscribe((equipamento) => {
                if (equipamento) {
                    this.messageService.add({
                        severity: 'success',
                        summary: 'Success',
                        detail: 'O equipamento ' + equipamento.nomeEquipamento + ' foi editado com sucesso!'
                    })
                    this.buscarEquipamentos()
                }
            });
        })
    }

    excluirEquipamento(id) {
        this.confirmationService.confirm({
            message: 'Tem certeza que deseja excluir o registro?',
            header: 'Confirmação de Exclusão',
            icon: 'pi pi-info-circle',
            acceptLabel: 'Sim',
            rejectLabel: 'Cancelar',
            accept: () => {
                this.equipamentoService.delete(id).subscribe(() => {
                    this.buscarEquipamentos();
                })
                this.messageService.add({severity: 'info', summary: 'Confirmação', detail: 'Equipamento inativado!'});
            }
        });
    }

}
