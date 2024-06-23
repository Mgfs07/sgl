import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {EventoModel} from "../../../../shared/models/evento.model";
import {SelectItem} from "../../../../shared/models/select-item";
import {EventoService} from "../../../../shared/services/evento.service";
import {MessageService} from "primeng/api";
import {DialogService, DynamicDialogConfig, DynamicDialogRef} from "primeng/dynamicdialog";
import {LocalService} from "../../../../shared/services/local.service";
import {AulaAlocacaoListModel} from "../../../../shared/models/aula-alocacao-list.model";

@Component({
  selector: 'app-alocacao-form',
  templateUrl: './alocacao-form.component.html',
  styleUrls: ['./alocacao-form.component.scss']
})
export class AlocacaoFormComponent implements OnInit {

    form: FormGroup;
    evento: EventoModel;
    isEdit: boolean;
    isVisualizar: boolean;
    locaisOptions: SelectItem[] = [];
    barcode: string='';
    values: string[] =[];

    constructor(
        private fb: FormBuilder,
        private service: EventoService,
        private messageService: MessageService,
        public dialogService: DialogService,
        private dialogConfig: DynamicDialogConfig,
        private localService: LocalService,
        public ref: DynamicDialogRef) {
        this.definirFormulario();
    }

    ngOnInit() {
        this.verificarAcao();
        this.buscarLocais();
    }

    fecharDialog(eventoSalvo?: EventoModel) {
        this.ref.close(eventoSalvo);
    }

    onKey(event: any) {
        this.barcode=event.target.value;
    }

    alocarAula() {
        const local = this.form.getRawValue();
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
        this.renderizarDadosAula();
    }

    private definirFormulario() {
        this.form = this.fb.group({
        });
    }

    private buscarLocais() {
        this.localService.buscarDropdownLocais().subscribe(value => this.locaisOptions = value)
    }

    private renderizarDadosAula() {
        const aulaEncontrada: AulaAlocacaoListModel = this.dialogConfig.data.aula;
        if (!aulaEncontrada) {
            return;
        }
        this.form.patchValue(aulaEncontrada);
    }


}
