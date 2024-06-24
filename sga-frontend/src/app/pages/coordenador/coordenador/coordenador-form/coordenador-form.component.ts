import {Component} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {CoordenadoriaModel} from "../../../../shared/models/coordenadoria.model";
import {MessageService} from "primeng/api";
import {DialogService, DynamicDialogConfig, DynamicDialogRef} from "primeng/dynamicdialog";
import {CoordenadorService} from "../../../../shared/services/coordenador.service";

@Component({
  selector: 'app-coordenador-form',
  templateUrl: './coordenador-form.component.html',
  styleUrls: ['./coordenador-form.component.scss']
})
export class CoordenadorFormComponent {

    form: FormGroup;
    coordenadoria: CoordenadoriaModel;
    isEdit: boolean;
    isVisualizar: boolean;
    motrarBotaoPesquisar: boolean = true;

    constructor(
        private fb: FormBuilder,
        private service: CoordenadorService,
        private messageService: MessageService,
        public dialogService: DialogService,
        private dialogConfig: DynamicDialogConfig,
        public ref: DynamicDialogRef) {
        this.definirFormulario();
    }

    ngOnInit() {
        this.verificarAcao();
    }

    fecharDialog(coordenadoriaSalva?: CoordenadoriaModel) {
        this.ref.close(coordenadoriaSalva);
    }

    salvarCoordenadoria() {
        const coordenadoria = this.form.getRawValue();
        this.service.insert(coordenadoria).subscribe(value => {
            this.fecharDialog(coordenadoria)
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
        this.renderizarDadosCoordenadoria();
    }

    private definirFormulario() {
        this.form = this.fb.group({
            matricula: [null],
            nome: [null, [Validators.required]]
        });
    }

    private renderizarDadosCoordenadoria() {
        const coordenadorEncontrado = this.dialogConfig.data.coordenador;
        if (!coordenadorEncontrado) {
            return;
        }
        this.form.patchValue(coordenadorEncontrado);
    }

    public buscarCoordenadorPorMatricula() {
        const matricula = this.form.get("matricula").value;
        this.service.findByMatricula(matricula).subscribe((value) => {
            this.form.patchValue(value);
        })

    }
}
