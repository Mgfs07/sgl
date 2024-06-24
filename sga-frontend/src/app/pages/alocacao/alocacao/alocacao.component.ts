import {Component, OnInit} from '@angular/core';
import {Column} from "../../../shared/models/colum.model";
import {DialogService, DynamicDialogRef} from "primeng/dynamicdialog";
import {ConfirmationService, MessageService, SelectItem} from "primeng/api";
import {EventoFormComponent} from "../../evento/evento/evento-form/evento-form.component";
import {AulaService} from "../../../shared/services/aula.service";
import {AulaAlocacaoListModel} from "../../../shared/models/aula-alocacao-list.model";
import {AlocacaoFormComponent} from "./alocacao-form/alocacao-form.component";
import {AulaAlocacaoModel} from "../../../shared/models/aula-alocacao.model";
import {AlocacaoLocalAulaModel} from "../../../shared/models/alocacao-local-aula.model";
import {TipoAtorBuscaEnum} from "../../../shared/enums/tipo-ator-busca.enum";
import {DisciplinaService} from "../../../shared/services/disciplina.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-alocacao',
  templateUrl: './alocacao.component.html',
  styleUrls: ['./alocacao.component.scss']
})
export class AlocacaoComponent implements OnInit {

    cols!: Column[];
    aulas: AulaAlocacaoListModel[];
    ref: DynamicDialogRef | undefined;
    display: boolean = false;
    disciplinaOptions: SelectItem[];
    form: FormGroup;


    constructor(public dialogService: DialogService,
                private messageService: MessageService,
                private service: AulaService,
                private confirmationService: ConfirmationService,
                private aulaService: AulaService,
                private disciplinaService: DisciplinaService,
                private fb: FormBuilder) {
        this.definirFormulario();
    }

    ngOnInit() {
        this.construirColunasListagem();
        this.buscarAulas();
        this.buscarDisciplinas();
    }

    private construirColunasListagem() {
        this.cols = [
            {field: 'nome', header: 'Nome', text: true},
            {field: 'horaInicio', header: 'Hora Início', text: true},
            {field: 'horaFim', header: 'Hora Fim', text: true},
            {field: 'professor', header: 'Professor', text: true},
            {field: 'local', header: 'Local', text: true},
            {field: 'acoes', header: 'Ações'}
        ];
    }

    public buscarAulas() {
        this.service.buscarAulasDisponiveis(this.form.get('idDisciplina').value).subscribe((value: AulaAlocacaoListModel[]) => {
            this.aulas = value;
        })
    }

    handleAcao(rowData: any, acao: string) {
        if (!rowData.local){
            this.service.findById(rowData.id).subscribe((value: AulaAlocacaoModel) => {
                this.ref = this.dialogService.open(AlocacaoFormComponent,
                    {
                        header: 'Formulário Alocação',
                        width: '35%',
                        data: {aula: value, acao: acao, idAula: rowData.id}
                    });
                this.ref.onClose.subscribe((aula) => {
                    if (aula) {
                        this.messageService.add({
                            severity: 'success',
                            summary: 'Success',
                            detail: 'Alocação realizada com sucesso!'
                        })
                        this.buscarAulas()
                    }
                });
            })
        }else {
            this.confirmationService.confirm({
                message: 'Tem certeza que deseja remover a alocação?',
                header: 'Confirmação',
                icon: 'pi pi-info-circle',
                acceptLabel: 'Sim',
                rejectLabel: 'Cancelar',
                accept: () => {
                    const alocacaoLocalAula: AlocacaoLocalAulaModel = new AlocacaoLocalAulaModel(null, rowData.id)
                    this.aulaService.alocarAula(alocacaoLocalAula)
                        .subscribe(() => {
                            this.buscarAulas()
                            this.messageService.add({severity: 'info', summary: 'Confirmação', detail: 'Alocação removida!'});
                        })
                }
            });
        }

    }

    getLabelAlocar(rowData: any) {
        if (!rowData.local) {
            return "Alocar"
        }
        return "Desalocar";
    }

    protected readonly TipoAtorBuscaEnum = TipoAtorBuscaEnum;

    private buscarDisciplinas() {
        this.disciplinaService.buscarDisciplinasDropdown().subscribe(value => this.disciplinaOptions = value)
    }

    private definirFormulario() {
        this.form = this.fb.group({
            idDisciplina: [null]
        });
    }
}
