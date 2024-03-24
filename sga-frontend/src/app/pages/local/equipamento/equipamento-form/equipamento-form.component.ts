import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {EquipamentoModel} from "../../../../shared/models/equipamento.model";
import {ConfirmationService, MessageService} from "primeng/api";
import {DialogService, DynamicDialogConfig, DynamicDialogRef} from "primeng/dynamicdialog";
import {EquipamentoService} from "../../../../shared/services/equipamento.service";

@Component({
    selector: 'app-equipamento-form',
    templateUrl: './equipamento-form.component.html',
    styleUrls: ['./equipamento-form.component.scss']
})
export class EquipamentoFormComponent implements OnInit {
    form: FormGroup;
    equipamento: EquipamentoModel;
    isEdit: boolean;
    isVisualizar: boolean;

    constructor(
        private fb: FormBuilder,
        private service: EquipamentoService,
        private messageService: MessageService,
        public dialogService: DialogService,
        private dialogConfig: DynamicDialogConfig,
        public ref: DynamicDialogRef) {
        this.definirFormulario();
    }

    ngOnInit() {
        this.verificarAcao();
    }

    fecharDialog(equipamentoSalvo?: EquipamentoModel) {
        this.ref.close(equipamentoSalvo);
    }

    salvarEquipamento() {
        const equipamento = this.form.getRawValue();
        this.service.insert(equipamento).subscribe(value => {
            this.fecharDialog(equipamento)
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
        this.renderizarDadosEquipamento();
    }

    private definirFormulario() {
        this.form = this.fb.group({
            id: [null],
            nome: [null, [Validators.required]]
        });
    }

    private renderizarDadosEquipamento() {
        const equipamentoEncontrado = this.dialogConfig.data.equipamento;
        if (!equipamentoEncontrado) {
            return;
        }
        this.form.patchValue(equipamentoEncontrado);
    }

}
