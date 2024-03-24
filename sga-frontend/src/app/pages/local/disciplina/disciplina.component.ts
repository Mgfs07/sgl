import {Component, OnInit} from '@angular/core';
import {Column} from "../../../shared/models/colum.model";
import {DialogService, DynamicDialogRef} from "primeng/dynamicdialog";
import {ConfirmationService, MessageService} from "primeng/api";
import {DisciplinaFormComponent} from "./disciplina-form/disciplina-form.component";
import {ex} from "@fullcalendar/core/internal-common";
import {DisciplinaService} from "../../../shared/services/disciplina.service";

@Component({
    selector: 'app-disciplina',
    templateUrl: './disciplina.component.html',
    styleUrls: ['./disciplina.component.scss'],
    providers: [DialogService, ConfirmationService, MessageService]

})
export class DisciplinaComponent implements OnInit {

    cols!: Column[];
    disciplinas: any;
    ref: DynamicDialogRef | undefined;
    display: boolean = false;


    constructor(public dialogService: DialogService,
                private messageService: MessageService,
                private disciplinaService: DisciplinaService,
                private confirmationService: ConfirmationService) {
    }

    ngOnInit() {
        this.construirColunasListagem();
        this.buscarDisciplinas()
    }

    private construirColunasListagem() {
        this.cols = [
            {field: 'nome', header: 'Nome', text: true},
            {field: 'acoes', header: 'Ações'}
        ];
    }

    private buscarDisciplinas() {
        this.disciplinaService.findAll().subscribe((value: any) => {
            this.disciplinas = value;
        })
    }

    novaDisciplina() {
        this.ref = this.dialogService.open(DisciplinaFormComponent,
            {
                header: 'Nova Disciplina',
                width: '35%',
                data: {acao: ''}
            });
        this.ref.onClose.subscribe((disciplina) => {
            if (disciplina) {
                this.messageService.add({
                    severity: 'success',
                    summary: 'Success',
                    detail: 'A disciplina ' + disciplina.nome + ' foi cadastrada com sucesso!'
                })
                this.buscarDisciplinas();
            }
        });
    }

    handleAcao(rowData: any, acao: string) {
        this.disciplinaService.findById(rowData.id).subscribe((value) => {
            this.ref = this.dialogService.open(DisciplinaFormComponent,
                {
                    header: 'Formulário Disciplina',
                    width: '35%',
                    data: {disciplina: value, acao: acao}
                });
            this.ref.onClose.subscribe((disciplina) => {
                if (disciplina) {
                    this.messageService.add({
                        severity: 'success',
                        summary: 'Success',
                        detail: 'A disciplina ' + disciplina.nome + ' foi editada com sucesso!'
                    })
                    this.buscarDisciplinas()
                }
            });
        })
    }

    excluirDisciplina(id) {
        this.confirmationService.confirm({
            message: 'Tem certeza que deseja excluir o registro?',
            header: 'Confirmação de Exclusão',
            icon: 'pi pi-info-circle',
            acceptLabel: 'Sim',
            rejectLabel: 'Cancelar',
            accept: () => {
                this.disciplinaService.delete(id).subscribe(() => {
                    this.buscarDisciplinas();
                })
                this.messageService.add({severity: 'info', summary: 'Confirmação', detail: 'Disciplina inativada!'});
            }
        });
    }

}
