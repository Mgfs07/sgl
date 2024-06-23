import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {SelectItem} from "../../../../shared/models/select-item";
import {EventoModel} from "../../../../shared/models/evento.model";
import {MessageService} from "primeng/api";
import {DialogService, DynamicDialogConfig, DynamicDialogRef} from "primeng/dynamicdialog";
import {LocalService} from "../../../../shared/services/local.service";
import {EventoService} from "../../../../shared/services/evento.service";
import {ProfessorService} from "../../../../shared/services/professor.service";
import {DropdownString} from "../../../../shared/util/dropdownString-util";

@Component({
  selector: 'app-evento-form',
  templateUrl: './evento-form.component.html',
  styleUrls: ['./evento-form.component.scss']
})
export class EventoFormComponent implements OnInit{

    form: FormGroup;
    evento: EventoModel;
    isEdit: boolean;
    isVisualizar: boolean;
    locais: SelectItem[] = [];
    dropdownProfessoresCoordenadores: DropdownString[] = [];
    barcode: string='';
    values: string[] =[];

    // TODO: ADICIONAR FORMA DE OBTER USUARIO LOGADO
    matriculaUsuarioLogado: string = "999";

    constructor(
        private fb: FormBuilder,
        private service: EventoService,
        private messageService: MessageService,
        public dialogService: DialogService,
        private dialogConfig: DynamicDialogConfig,
        private localService: LocalService,
        private professorService: ProfessorService,
        public ref: DynamicDialogRef) {
        this.definirFormulario();
    }

    ngOnInit() {
        this.verificarAcao();
        this.buscarCoordenadorias();
        this.buscarDropdownProfessoresCoordenadores();
    }

    fecharDialog(eventoSalvo?: EventoModel) {
        this.ref.close(eventoSalvo);
    }

    onKey(event: any) {
        this.barcode=event.target.value;
    }

    salvarEvento() {
        const evento = this.form.getRawValue();
        this.service.insert(evento).subscribe(value => {
            this.fecharDialog(evento)
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
        this.renderizarDadosEvento();

    }

    private definirFormulario() {
        this.form = this.fb.group({
            id: [null],
            matriculaSolicitante: [null, [Validators.required]],
            matriculaUsuarioLogado: [this.matriculaUsuarioLogado],
            idLocal: [null, [Validators.required]],
            descricao: [null, [Validators.required]],
            horaInicio: [null, [Validators.required]],
            horaFim: [null, [Validators.required]],
            data: [null, [Validators.required]]
        });
    }

    private buscarCoordenadorias() {
        this.localService.buscarDropdownLocais().subscribe(value => this.locais = value)
    }

    private renderizarDadosEvento() {
        const eventoEncontrado: EventoModel = this.dialogConfig.data.evento;
        if (!eventoEncontrado) {
            return;
        }
        this.form.patchValue(eventoEncontrado);
        this.form.get('matriculaUsuarioLogado').setValue(this.matriculaUsuarioLogado);
    }

    private buscarDropdownProfessoresCoordenadores() {
        this.professorService.buscarDropdownProfessoresEcoordenadores().subscribe(value => this.dropdownProfessoresCoordenadores = value)
    }

}
