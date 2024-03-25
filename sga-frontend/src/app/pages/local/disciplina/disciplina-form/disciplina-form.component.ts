import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MessageService} from "primeng/api";
import {DialogService, DynamicDialogConfig, DynamicDialogRef} from "primeng/dynamicdialog";
import {DisciplinaModel} from "../../../../shared/models/disciplina.model";
import {DisciplinaService} from "../../../../shared/services/disciplina.service";

@Component({
    selector: 'app-disciplina-form',
    templateUrl: './disciplina-form.component.html',
    styleUrls: ['./disciplina-form.component.scss']
})
export class DisciplinaFormComponent implements OnInit {
    form: FormGroup;
    disciplina: DisciplinaModel;
    isEdit: boolean;
    isVisualizar: boolean;

    constructor(
        private fb: FormBuilder,
        private service: DisciplinaService,
        private messageService: MessageService,
        public dialogService: DialogService,
        private dialogConfig: DynamicDialogConfig,
        public ref: DynamicDialogRef) {
        this.definirFormulario();
    }

    ngOnInit() {
        this.verificarAcao();
    }

    fecharDialog(disciplinaSalva?: DisciplinaModel) {
        this.ref.close(disciplinaSalva);
    }

    salvarDisciplina() {
        const disciplina = this.form.getRawValue();
        this.service.insert(disciplina).subscribe(value => {
            this.fecharDialog(disciplina)
        }, (error) => {
            this.form.get('nome').setErrors({'invalid': true});
        })
    }

    private verificarAcao() {
        if (this.dialogConfig.data.acao == 'visualizar') {
            this.form.disable();
            this.isVisualizar = true;
        }
        if (this.dialogConfig.data.acao == 'editar') {
            this.form.enable();
            this.isEdit = true;
        }
        this.renderizarDadosDisciplina();
    }

    private definirFormulario() {
        this.form = this.fb.group({
            id: [null],
            nome: [null, [Validators.required]]
        });
    }

    private renderizarDadosDisciplina() {
        const disciplinaEncontrada = this.dialogConfig.data.disciplina;
        if (!disciplinaEncontrada) {
            return;
        }
        this.form.patchValue(disciplinaEncontrada);
    }

}
