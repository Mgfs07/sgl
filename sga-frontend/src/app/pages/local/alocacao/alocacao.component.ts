import {Component, OnInit} from '@angular/core';
import {Column} from "../../../shared/models/colum.model";
import {DialogService, DynamicDialogRef} from "primeng/dynamicdialog";
import {ConfirmationService, MessageService, SelectItem} from "primeng/api";
import {EventoFormComponent} from "../evento/evento-form/evento-form.component";
import {AulaService} from "../../../shared/services/aula.service";
import {AulaAlocacaoListModel} from "../../../shared/models/aula-alocacao-list.model";
import {AlocacaoFormComponent} from "./alocacao-form/alocacao-form.component";
import {AulaAlocacaoModel} from "../../../shared/models/aula-alocacao.model";
import {AlocacaoLocalAulaModel} from "../../../shared/models/alocacao-local-aula.model";
import {TipoAtorBuscaEnum} from "../../../shared/enums/tipo-ator-busca.enum";

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


    constructor(public dialogService: DialogService,
                private messageService: MessageService,
                private service: AulaService,
                private confirmationService: ConfirmationService,
                private aulaService: AulaService) {
    }

    ngOnInit() {
        this.construirColunasListagem();
        this.buscarAulas()
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

    private buscarAulas() {
        this.service.buscarAulasDisponiveis().subscribe((value: AulaAlocacaoListModel[]) => {
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
                            detail: 'O evento foi editado com sucesso!'
                        })
                        this.buscarAulas()
                    }
                });
            })
        }else {
            this.confirmationService.confirm({
                message: 'Tem certeza que deseja remover a alocação?',
                header: 'Confirmação de Exclusão',
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
}
