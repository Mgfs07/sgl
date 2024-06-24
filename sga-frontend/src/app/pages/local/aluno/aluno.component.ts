import {Component, OnInit} from '@angular/core';
import {Column} from "../../../shared/models/colum.model";
import {DialogService, DynamicDialogRef} from "primeng/dynamicdialog";
import {ConfirmationService, MessageService} from "primeng/api";
import {AlunoService} from "../../../shared/services/aluno.service";
import {AlunoFormComponent} from "./aluno-form/aluno-form.component";

@Component({
    selector: 'app-aluno',
    templateUrl: './aluno.component.html',
    styleUrls: ['./aluno.component.scss'],
    providers: [DialogService, ConfirmationService, MessageService]

})
export class AlunoComponent implements OnInit{
    cols!: Column[];
    alunos: any;
    ref: DynamicDialogRef | undefined;
    display: boolean = false;


    constructor(public dialogService: DialogService,
                private messageService: MessageService,
                private alunoService: AlunoService,
                private confirmationService: ConfirmationService) {
    }

    ngOnInit() {
        this.construirColunasListagem();
        this.buscarAlunos()
    }

    private construirColunasListagem() {
        this.cols = [
            {field: 'nome', header: 'Nome', text: true},
            {field: 'matricula', header: 'Matrícula', text: true},
            {field: 'codigoBarra', header: 'Código de Barras', text: true},
            {field: 'acoes', header: 'Ações'}
        ];
    }

    private buscarAlunos() {
        this.alunoService.findAll().subscribe((value: any) => {
            this.alunos = value;
        })
    }

    novoAluno() {
        this.ref = this.dialogService.open(AlunoFormComponent,
            {
                header: 'Novo Aluno',
                width: '35%',
                data: {acao: ''}
            });
        this.ref.onClose.subscribe((aluno) => {
            if (aluno) {
                this.messageService.add({
                    severity: 'success',
                    summary: 'Success',
                    detail: 'O aluno ' + aluno.nome + ' foi cadastrado com sucesso!'
                })
                this.buscarAlunos();
            }
        });
    }

    handleAcao(rowData: any, acao: string) {
        this.alunoService.findById(rowData.matricula).subscribe((value) => {
            this.ref = this.dialogService.open(AlunoFormComponent,
                {
                    header: 'Formulário Aluno',
                    width: '35%',
                    data: {aluno: value, acao: acao}
                });
            this.ref.onClose.subscribe((aluno) => {
                if (aluno) {
                    this.messageService.add({
                        severity: 'success',
                        summary: 'Success',
                        detail: 'O aluno ' + aluno.nome + ' foi editado com sucesso!'
                    })
                    this.buscarAlunos()
                }
            });
        })
    }

    excluirAluno(matricula) {
        this.confirmationService.confirm({
            message: 'Tem certeza que deseja excluir o registro?',
            header: 'Confirmação de Exclusão',
            icon: 'pi pi-info-circle',
            acceptLabel: 'Sim',
            rejectLabel: 'Cancelar',
            accept: () => {
                this.alunoService.deleteByMatricula(matricula).subscribe(() => {
                    this.buscarAlunos();
                })
                this.messageService.add({severity: 'info', summary: 'Confirmação', detail: 'Aluno inativado!'});
            }
        });
    }
}
