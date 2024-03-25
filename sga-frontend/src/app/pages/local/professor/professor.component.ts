import {Component, OnInit} from '@angular/core';
import {Column} from "../../../shared/models/colum.model";
import {DialogService, DynamicDialogRef} from "primeng/dynamicdialog";
import {ConfirmationService, MessageService} from "primeng/api";
import {ProfessorFormComponent} from "./professor-form/professor-form.component";
import {ProfessorListModel} from "../../../shared/models/professor-list.model";
import {ProfessorService} from "../../../shared/services/professor.service";

@Component({
    selector: 'app-professor',
    templateUrl: './professor.component.html',
    styleUrls: ['./professor.component.scss'],
    providers: [DialogService, ConfirmationService, MessageService]
})
export class ProfessorComponent implements OnInit {

    cols!: Column[];
    professores: ProfessorListModel[];
    ref: DynamicDialogRef | undefined;
    display: boolean = false;


    constructor(public dialogService: DialogService,
                private messageService: MessageService,
                private service: ProfessorService,
                private confirmationService: ConfirmationService) {
    }

    ngOnInit() {
        this.construirColunasListagem();
        this.buscarProfessores()
    }

    private construirColunasListagem() {
        this.cols = [
            {field: 'nome', header: 'Nome', text: true},
            {field: 'nomeCoordenadoria', header: 'Coordenadoria', text: true},
            {field: 'acoes', header: 'Ações'}
        ];
    }

    private buscarProfessores() {
        this.service.findAll().subscribe((value) => {
            this.professores = value;
        })
    }

    novoProfessor() {
        this.ref = this.dialogService.open(ProfessorFormComponent,
            {
                header: 'Novo Período',
                width: '18%',
                data: {acao: ''}
            });
        this.ref.onClose.subscribe((professor) => {
            if (professor) {
                this.messageService.add({
                    severity: 'success',
                    summary: 'Success',
                    detail: 'O professor ' + professor.nome + ' foi cadastrado com sucesso!'
                })
                this.buscarProfessores();
            }
        });
    }

    handleAcao(rowData: any, acao: string) {
        this.service.findById(rowData.id).subscribe((value) => {
            this.ref = this.dialogService.open(ProfessorFormComponent,
                {
                    header: 'Formulário Professor',
                    width: '18%',
                    data: {professor: value, acao: acao}
                });
            this.ref.onClose.subscribe((professor) => {
                if (professor) {
                    this.messageService.add({
                        severity: 'success',
                        summary: 'Success',
                        detail: 'O professor ' + professor.nome + ' foi editado com sucesso!'
                    })
                    this.buscarProfessores()
                }
            });
        })
    }

    excluirProfessor(id) {
        this.confirmationService.confirm({
            message: 'Tem certeza que deseja excluir o registro?',
            header: 'Confirmação de Exclusão',
            icon: 'pi pi-info-circle',
            acceptLabel: 'Sim',
            rejectLabel: 'Cancelar',
            accept: () => {
                this.service.delete(id).subscribe(() => {
                    this.buscarProfessores();
                })
                this.messageService.add({severity: 'info', summary: 'Confirmação', detail: 'Professor inativado!'});
            }
        });
    }

}
