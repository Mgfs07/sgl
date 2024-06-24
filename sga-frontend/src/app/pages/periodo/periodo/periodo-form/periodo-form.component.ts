import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MessageService} from "primeng/api";
import {DialogService, DynamicDialogConfig, DynamicDialogRef} from "primeng/dynamicdialog";
import {PeriodoModel} from "../../../../shared/models/periodo.model";
import {PeriodoService} from "../../../../shared/services/periodo.service";

@Component({
    selector: 'app-periodo-form',
    templateUrl: './periodo-form.component.html',
    styleUrls: ['./periodo-form.component.scss']
})
export class PeriodoFormComponent implements OnInit {
    form: FormGroup;
    periodo: PeriodoModel;
    isEdit: boolean;
    isVisualizar: boolean;

    constructor(
        private fb: FormBuilder,
        private service: PeriodoService,
        private messageService: MessageService,
        public dialogService: DialogService,
        private dialogConfig: DynamicDialogConfig,
        public ref: DynamicDialogRef) {
        this.definirFormulario();
    }

    ngOnInit() {
        this.verificarAcao();
    }

    fecharDialog(periodoSalvo?: PeriodoModel) {
        this.ref.close(periodoSalvo);
    }

    salvarPeriodo() {
        const periodo = this.form.getRawValue();
        this.service.insert(periodo).subscribe(value => {
            this.fecharDialog(periodo)
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
        this.renderizarDadosPeriodo();
    }

    private definirFormulario() {
        this.form = this.fb.group({
            id: [null],
            descricao: [null, [Validators.required]],
            dataInicio: [null, [Validators.required]],
            dataFim: [null, [Validators.required]]
        });
    }

    private renderizarDadosPeriodo() {
        const periodoEncontrado: PeriodoModel = this.dialogConfig.data.periodo;
        if (!periodoEncontrado) {
            return;
        }
        periodoEncontrado.dataInicio = new Date(periodoEncontrado.dataInicio)
        periodoEncontrado.dataFim = new Date(periodoEncontrado.dataFim)
        this.form.patchValue(periodoEncontrado);
    }

}
