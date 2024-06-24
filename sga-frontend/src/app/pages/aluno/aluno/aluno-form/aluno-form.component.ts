import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MessageService} from "primeng/api";
import {DialogService, DynamicDialogConfig, DynamicDialogRef} from "primeng/dynamicdialog";
import {AlunoModel} from "../../../../shared/models/aluno.model";
import {AlunoService} from "../../../../shared/services/aluno.service";
import {AlunoComponent} from "../aluno.component";

@Component({
  selector: 'app-aluno-form',
  templateUrl: './aluno-form.component.html',
  styleUrls: ['./aluno-form.component.scss']
})
export class AlunoFormComponent implements OnInit{

    form: FormGroup;
    aluno: AlunoModel;
    isEdit: boolean;
    isVisualizar: boolean;
    motrarBotaoPesquisar: boolean = true;

    constructor(
        private fb: FormBuilder,
        private service: AlunoService,
        private messageService: MessageService,
        public dialogService: DialogService,
        private dialogConfig: DynamicDialogConfig,
        public ref: DynamicDialogRef) {
        this.definirFormulario();
    }

    ngOnInit() {
        this.verificarAcao();
    }

    fecharDialog(alunoSalvo?: AlunoComponent) {
        this.ref.close(alunoSalvo);
    }

    salvarAluno() {
        const aluno = this.form.getRawValue();
        this.service.insert(aluno).subscribe(value => {
            this.fecharDialog(aluno)
        }, (error) => {
            this.form.get('nome').setErrors({'invalid': true});
        })
    }

    private verificarAcao() {
        if (this.dialogConfig.data.acao == 'visualizar') {
            this.form.disable();
            this.isVisualizar = true;
            this.motrarBotaoPesquisar = false;
        }
        if (this.dialogConfig.data.acao == 'editar') {
            this.form.enable();
            this.isEdit = true;
            this.motrarBotaoPesquisar = false;
        }
        this.renderizarDadosAluno();
    }

    private definirFormulario() {
        this.form = this.fb.group({
            id: [null],
            matricula: [null, [Validators.required]],
            nome: [null, [Validators.required]],
            codigoBarra: [null, [Validators.required]]
        });
    }

    private renderizarDadosAluno() {
        const alunoEncontrado = this.dialogConfig.data.aluno;
        if (!alunoEncontrado) {
            return;
        }
        this.form.patchValue(alunoEncontrado);
    }

    public buscarAlunoPorMatricula() {
        const matricula = this.form.get("matricula").value;
        this.service.findByMatricula(matricula).subscribe((value) => {
            this.form.patchValue(value);
        })

    }

}
