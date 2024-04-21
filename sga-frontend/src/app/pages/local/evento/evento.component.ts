import {Component, OnInit} from '@angular/core';
import {Column} from "../../../shared/models/colum.model";
import {ProfessorListModel} from "../../../shared/models/professor-list.model";
import {DialogService, DynamicDialogRef} from "primeng/dynamicdialog";
import {ConfirmationService, MessageService} from "primeng/api";
import {EventoService} from "../../../shared/services/evento.service";
import {EventoListModel} from "../../../shared/models/evento-list.model";
import {ProfessorFormComponent} from "../professor/professor-form/professor-form.component";
import {EventoFormComponent} from "./evento-form/evento-form.component";

@Component({
  selector: 'app-evento',
  templateUrl: './evento.component.html',
  styleUrls: ['./evento.component.scss']
})
export class EventoComponent implements OnInit{

    cols!: Column[];
    eventos: EventoListModel[];
    ref: DynamicDialogRef | undefined;
    display: boolean = false;


    constructor(public dialogService: DialogService,
                private messageService: MessageService,
                private service: EventoService,
                private confirmationService: ConfirmationService) {
    }

    ngOnInit() {
        this.construirColunasListagem();
        this.buscarEventos()
    }

    private construirColunasListagem() {
        this.cols = [
            {field: 'descricao', header: 'Descrição', text: true},
            {field: 'nomeLocal', header: 'Local', text: true},
            {field: 'data', header: 'Data', text: true},
            {field: 'horaInicio', header: 'Hora Início', text: true},
            {field: 'horaFim', header: 'Hora Fim', text: true},
            {field: 'acoes', header: 'Ações'}
        ];
    }

    private buscarEventos() {
        this.service.findAll().subscribe((value) => {
            this.eventos = value;
        })
    }

    novoEvento() {
        this.ref = this.dialogService.open(EventoFormComponent,
            {
                header: 'Novo Evento',
                width: '35%',
                data: {acao: ''}
            });
        this.ref.onClose.subscribe((evento) => {
            if (evento) {
                this.messageService.add({
                    severity: 'success',
                    summary: 'Success',
                    detail: 'O evento foi cadastrado com sucesso!'
                })
                this.buscarEventos();
            }
        });
    }

    handleAcao(rowData: any, acao: string) {
        this.service.findById(rowData.id).subscribe((value) => {
            this.ref = this.dialogService.open(EventoFormComponent,
                {
                    header: 'Formulário Evento',
                    width: '35%',
                    data: {evento: value, acao: acao}
                });
            this.ref.onClose.subscribe((evento) => {
                if (evento) {
                    this.messageService.add({
                        severity: 'success',
                        summary: 'Success',
                        detail: 'O evento foi editado com sucesso!'
                    })
                    this.buscarEventos()
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
                    this.buscarEventos();
                })
                this.messageService.add({severity: 'info', summary: 'Confirmação', detail: 'Evento inativado!'});
            }
        });
    }
}
