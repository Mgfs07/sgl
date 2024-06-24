import {Component, OnInit} from '@angular/core';
import {Column} from "../../../shared/models/colum.model";
import {DialogService, DynamicDialogRef} from "primeng/dynamicdialog";
import {ConfirmationService, MessageService} from "primeng/api";
import {PeriodoService} from "../../../shared/services/periodo.service";
import {PeriodoListModel} from "../../../shared/models/periodo-list.model";
import {PeriodoFormComponent} from "./periodo-form/periodo-form.component";

@Component({
    selector: 'app-periodo',
    templateUrl: './periodo.component.html',
    styleUrls: ['./periodo.component.scss'],
    providers: [DialogService, ConfirmationService, MessageService]
})
export class PeriodoComponent implements OnInit {

    cols!: Column[];
    periodos: PeriodoListModel[];
    ref: DynamicDialogRef | undefined;
    display: boolean = false;


    constructor(public dialogService: DialogService,
                private messageService: MessageService,
                private service: PeriodoService,
                private confirmationService: ConfirmationService) {
    }

    ngOnInit() {
        this.construirColunasListagem();
        this.buscarPeriodos()
    }

    private construirColunasListagem() {
        this.cols = [
            {field: 'descricao', header: 'Descrição', text: true},
            {field: 'dataInicio', header: 'Início'},
            {field: 'dataFim', header: 'Fim'},
            {field: 'acoes', header: 'Ações'}
        ];
    }

    private buscarPeriodos() {
        this.service.findAll().subscribe((value: PeriodoListModel[]) => {
            this.periodos = value;
        })
    }

    novoPeriodo() {
        this.ref = this.dialogService.open(PeriodoFormComponent,
            {
                header: 'Novo Período',
                width: '35%',
                data: {acao: ''}
            });
        this.ref.onClose.subscribe((periodo) => {
            if (periodo) {
                this.messageService.add({
                    severity: 'success',
                    summary: 'Success',
                    detail: 'O período ' + periodo.nome + ' foi cadastrado com sucesso!'
                })
                this.buscarPeriodos();
            }
        });
    }

    handleAcao(rowData: any, acao: string) {
        this.service.findById(rowData.id).subscribe((value) => {
            this.ref = this.dialogService.open(PeriodoFormComponent,
                {
                    header: 'Formulário Período',
                    width: '35%',
                    data: {periodo: value, acao: acao}
                });
            this.ref.onClose.subscribe((periodo) => {
                if (periodo) {
                    this.messageService.add({
                        severity: 'success',
                        summary: 'Success',
                        detail: 'O período ' + periodo.descricao + ' foi editado com sucesso!'
                    })
                    this.buscarPeriodos()
                }
            });
        })
    }

    excluirPeriodo(id) {
        this.confirmationService.confirm({
            message: 'Tem certeza que deseja excluir o registro?',
            header: 'Confirmação de Exclusão',
            icon: 'pi pi-info-circle',
            acceptLabel: 'Sim',
            rejectLabel: 'Cancelar',
            accept: () => {
                this.service.delete(id).subscribe(() => {
                    this.buscarPeriodos();
                })
                this.messageService.add({severity: 'info', summary: 'Confirmação', detail: 'Período inativado!'});
            }
        });
    }

}
