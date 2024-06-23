import {Component, OnInit} from '@angular/core';
import {Column} from "../../../shared/models/colum.model";
import {DialogService, DynamicDialogRef} from "primeng/dynamicdialog";
import {ConfirmationService, MessageService} from "primeng/api";
import {EventoFormComponent} from "../evento/evento-form/evento-form.component";
import {AulaService} from "../../../shared/services/aula.service";
import {AulaAlocacaoListModel} from "../../../shared/models/aula-alocacao-list.model";
import {AlocacaoFormComponent} from "./alocacao-form/alocacao-form.component";

@Component({
  selector: 'app-alocacao',
  templateUrl: './alocacao.component.html',
  styleUrls: ['./alocacao.component.scss']
})
export class AlocacaoComponent implements OnInit {

    cols!: Column[];
    aulas: AulaAlocacaoListModel[];
    ref: DynamicDialogRef | undefined;
    display: boolean = false;

    constructor(public dialogService: DialogService,
                private messageService: MessageService,
                private service: AulaService,
                private confirmationService: ConfirmationService) {
    }

    ngOnInit() {
        this.construirColunasListagem();
        this.buscarAulas()
    }

    private construirColunasListagem() {
        this.cols = [
            {field: 'nome', header: 'Nome', text: true},
            {field: 'horaInicio', header: 'Hora Início', text: true},
            {field: 'horaFim', header: 'Hora Fim', text: true},
            {field: 'professor', header: 'Professor', text: true},
            {field: 'local', header: 'Local', text: true},
            {field: 'acoes', header: 'Ações'}
        ];
    }

    private buscarAulas() {
        this.service.buscarAulasDisponiveis().subscribe((value: AulaAlocacaoListModel[]) => {
            this.aulas = value;
        })
    }

    handleAcao(rowData: any, acao: string) {
        this.service.findById(rowData.id).subscribe((value) => {
            this.ref = this.dialogService.open(AlocacaoFormComponent,
                {
                    header: 'Formulário Alocação',
                    width: '35%',
                    data: {alocacao: value, acao: acao}
                });
            this.ref.onClose.subscribe((alocacao) => {
                if (alocacao) {
                    this.messageService.add({
                        severity: 'success',
                        summary: 'Success',
                        detail: 'O evento foi editado com sucesso!'
                    })
                    this.buscarAulas()
                }
            });
        })
    }

    excluirEvento(id) {
        this.confirmationService.confirm({
            message: 'Tem certeza que deseja excluir o registro?',
            header: 'Confirmação de Exclusão',
            icon: 'pi pi-info-circle',
            acceptLabel: 'Sim',
            rejectLabel: 'Cancelar',
            accept: () => {
                this.service.delete(id).subscribe(() => {
                    this.buscarAulas();
                })
                this.messageService.add({severity: 'info', summary: 'Confirmação', detail: 'Evento inativado!'});
            }
        });
    }



}
