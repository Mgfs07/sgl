import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MessageService} from "primeng/api";
import {DialogService, DynamicDialogConfig, DynamicDialogRef} from "primeng/dynamicdialog";
import {ProfessorModel} from "../../../../shared/models/professor.model";
import {ProfessorService} from "../../../../shared/services/professor.service";
import {SelectItem} from "../../../../shared/models/select-item";
import {CoordenadoriaService} from "../../../../shared/services/coordenadoria.service";

@Component({
    selector: 'app-professor-form',
    templateUrl: './professor-form.component.html',
    styleUrls: ['./professor-form.component.scss']
})
export class ProfessorFormComponent implements OnInit {
    form: FormGroup;
    professor: ProfessorModel;
    isEdit: boolean;
    isVisualizar: boolean;
    motrarBotaoPesquisar: boolean = true;
    coordenadorias: SelectItem[] = [];

    constructor(
        private fb: FormBuilder,
        private service: ProfessorService,
        private messageService: MessageService,
        public dialogService: DialogService,
        private dialogConfig: DynamicDialogConfig,
        private coordenadoriasService: CoordenadoriaService,
        public ref: DynamicDialogRef) {
        this.definirFormulario();
    }

    ngOnInit() {
        this.verificarAcao();
        this.buscarCoordenadorias();
    }

    fecharDialog(professorSalvo?: ProfessorModel) {
        this.ref.close(professorSalvo);
    }

    salvarProfessor() {
        const professor = this.form.getRawValue();
        if(this.isEdit) {
            this.service.update(professor).subscribe(value => {
                this.fecharDialog(professor)
            })
            return;
        }
        this.service.insert(professor).subscribe(value => {
            this.fecharDialog(professor)
        })
    }

    private verificarAcao() {
        if (this.dialogConfig.data.acao == 'visualizar') {
            this.form.disable();
            this.isVisualizar = true;
            this.motrarBotaoPesquisar = false
        }
        if (this.dialogConfig.data.acao == 'editar') {
            this.form.enable();
            this.isEdit = true;
            this.motrarBotaoPesquisar = false
        }
        this.renderizarDadosProfessor();

    }

    private definirFormulario() {
        this.form = this.fb.group({
            matricula: [null],
            nome: [null, [Validators.required]],
            idCoordenadoria: [null],
            rfid: [null],
        });
    }

    private buscarCoordenadorias() {
        this.coordenadoriasService.buscarDropdown().subscribe(value => this.coordenadorias = value)
    }

    private renderizarDadosProfessor() {
        const professorEncontrado: ProfessorModel = this.dialogConfig.data.professor;
        if (!professorEncontrado) {
            return;
        }
        this.form.patchValue(professorEncontrado);
    }

    public buscarProfessorPorMatricula() {
        const matricula = this.form.get("matricula").value;
        this.service.findByMatricula(matricula).subscribe((value) => {
            this.form.patchValue(value);
        })
    }
}
