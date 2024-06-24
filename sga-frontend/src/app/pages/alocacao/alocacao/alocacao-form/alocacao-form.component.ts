import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {EventoModel} from "../../../../shared/models/evento.model";
import {SelectItem} from "../../../../shared/models/select-item";
import {EventoService} from "../../../../shared/services/evento.service";
import {MessageService} from "primeng/api";
import {DialogService, DynamicDialogConfig, DynamicDialogRef} from "primeng/dynamicdialog";
import {LocalService} from "../../../../shared/services/local.service";
import {AulaAlocacaoModel} from "../../../../shared/models/aula-alocacao.model";
import {AlocacaoLocalAulaModel} from "../../../../shared/models/alocacao-local-aula.model";
import {AulaService} from "../../../../shared/services/aula.service";

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
        public ref: DynamicDialogRef,
        private aulaService: AulaService) {
        this.definirFormulario();
    }

    ngOnInit() {
        this.verificarAcao();
    }

    fecharDialog(alocacaoSalva?: AlocacaoLocalAulaModel) {
        this.ref.close(alocacaoSalva);
    }

    onKey(event: any) {
        this.barcode=event.target.value;
    }

    alocarAula() {
        const idLocal: number = this.form.get('idLocal').value;
        const idAula: number = this.dialogConfig.data.idAula;
        const alocacaoLocalAula: AlocacaoLocalAulaModel = new AlocacaoLocalAulaModel(idLocal, idAula);

        this.aulaService.alocarAula(alocacaoLocalAula)
            .subscribe(() => this.fecharDialog(alocacaoLocalAula) )

    }

    private verificarAcao() {
        this.renderizarDadosAula();
    }

    private definirFormulario() {
        this.form = this.fb.group({
            id: [{value: null, disabled: true}],
            disciplina: [{value: null, disabled: true}],
            horaInicio: [{value: null, disabled: true}],
            horaFim: [{value: null, disabled: true}],
            idLocal: [null],
            professor: [{value: null, disabled: true}],
            diaSemana: [{value: null, disabled: true}]
        });
    }

    private buscarLocais(idLocal: number) {
        this.localService.buscarDropdownLocalAlocacao(idLocal).subscribe(value => this.locaisOptions = value)
    }

    private renderizarDadosAula() {
        this.buscarLocais(this.dialogConfig.data.idAula)
        const aulaEncontrada: AulaAlocacaoModel = this.dialogConfig.data.aula;
        if (!aulaEncontrada) {
            return;
        }
        this.form.patchValue(aulaEncontrada);
    }


}
