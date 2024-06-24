import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuditoriaEventoModel} from "../../../../shared/models/auditoria-evento.model";
import {DynamicDialogConfig, DynamicDialogRef} from "primeng/dynamicdialog";
import {EventoModel} from "../../../../shared/models/evento.model";

@Component({
  selector: 'app-auditoria-evento-modal',
  templateUrl: './auditoria-evento-modal.component.html',
  styleUrls: ['./auditoria-evento-modal.component.scss']
})
export class AuditoriaEventoModalComponent implements OnInit{

    form: FormGroup;
    auditoria: AuditoriaEventoModel;

    ngOnInit() {
        this.form.disable();
        this.renderizarDadosAuditoria();
    }

    constructor(
        private fb: FormBuilder,
        private dialogConfig: DynamicDialogConfig,
        public ref: DynamicDialogRef
    ) {
        this.definirFormulario();
    }

    private definirFormulario() {
        this.form = this.fb.group({
            id: [null],
            nomeUsuarioAlteracao: [null],
            acao: [null]
        });
    }

    private renderizarDadosAuditoria() {
        const auditoriaEventoModel: AuditoriaEventoModel = this.dialogConfig.data.auditoria;
        if (!auditoriaEventoModel) {
            return;
        }
        this.form.patchValue(auditoriaEventoModel);
    }


}
