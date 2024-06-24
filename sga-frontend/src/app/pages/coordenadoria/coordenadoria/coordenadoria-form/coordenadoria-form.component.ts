import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MessageService} from "primeng/api";
import {DialogService, DynamicDialogConfig, DynamicDialogRef} from "primeng/dynamicdialog";
import {CoordenadoriaModel} from "../../../../shared/models/coordenadoria.model";
import {CoordenadoriaService} from "../../../../shared/services/coordenadoria.service";

@Component({
    selector: 'app-coordenadoria-form',
    templateUrl: './coordenadoria-form.component.html',
    styleUrls: ['./coordenadoria-form.component.scss']
})
export class CoordenadoriaFormComponent implements OnInit{
    form: FormGroup;
    coordenadoria: CoordenadoriaModel;
    isEdit: boolean;
    isVisualizar: boolean;

    constructor(
        private fb: FormBuilder,
        private service: CoordenadoriaService,
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
            id: [null],
            nome: [null, [Validators.required]]
        });
    }

    private renderizarDadosCoordenadoria() {
        const coordenadoriaEncontrada = this.dialogConfig.data.coordenadoria;
        if (!coordenadoriaEncontrada) {
            return;
        }
        this.form.patchValue(coordenadoriaEncontrada);
    }
}
