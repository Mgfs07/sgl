import {Component, ViewChild} from '@angular/core';
import {Column} from "../../../shared/models/colum.model";
import {DialogService, DynamicDialogRef} from "primeng/dynamicdialog";
import {ConfirmationService, MessageService, SelectItem} from "primeng/api";
import {HorariosAlunoService} from "../../../shared/services/horarios-aluno.service";
import {BlockUI, NgBlockUI} from 'ng-block-ui';
import {HorarioModel} from '../../../shared/components/grid-horario/horario.model';
import {AulaModel} from '../../../shared/components/grid-horario/aula.model';
import {ProfessorService} from "../../../shared/services/professor.service";
import {FormBuilder, FormGroup} from "@angular/forms";
import {HorarioFiltroModel} from "../../../shared/models/horario-filtro.model";
import {TipoAtorBuscaEnum} from "../../../shared/enums/tipo-ator-busca.enum";
import {EquipamentoFormComponent} from "../equipamento/equipamento-form/equipamento-form.component";
import {
    ImpressaoRelatorioHorariosComponent
} from "../../../shared/components/impressao-relatorio-horarios/impressao-relatorio-horarios.component";

@Component({
    selector: 'app-horarios-aluno',
    templateUrl: './horarios-aluno.component.html',
    styleUrls: ['./horarios-aluno.component.scss'],
    providers: [DialogService, ConfirmationService, MessageService]
})
export class HorariosAlunoComponent {

    @BlockUI() blockUI: NgBlockUI;
    @ViewChild(ImpressaoRelatorioHorariosComponent) impressaoRelatorio: ImpressaoRelatorioHorariosComponent;
    cols!: Column[];
    horario: HorarioModel[];
    ref: DynamicDialogRef | undefined;
    display: boolean = false;
    matricula: string;
    horarios1: HorarioModel[];
    protected readonly TipoAtorBuscaEnum = TipoAtorBuscaEnum;
    tipoAtorGrid: TipoAtorBuscaEnum;
    professorOptions: SelectItem[];
    form: FormGroup;
    indexAbas: number = 0;
    turmaOptions: SelectItem[];
    horarioFiltroModel: HorarioFiltroModel = new HorarioFiltroModel();
    horarioRelatorio: boolean = false;


    constructor(public dialogService: DialogService,
                private messageService: MessageService,
                private service: HorariosAlunoService,
                private professorService: ProfessorService,
                private fb: FormBuilder) {
        this.definirFormulario();
    }

    ngOnInit() {
        this.professorService.buscarDropdown().subscribe(value => this.professorOptions = value);
        this.service.buscarDropdownTurmas().subscribe(value => this.turmaOptions = value);
    }

    buscarHorarios(tipoAtorBusca: TipoAtorBuscaEnum) {
        this.construirFiltro(tipoAtorBusca);
        this.service.buscarHorario(this.horarioFiltroModel).subscribe((horarios: HorarioModel[]) => {
            horarios.forEach((horario: HorarioModel) => {
                horario.aulas.forEach((aula: AulaModel) => {
                    aula.horaInicio = new Date('1970-01-01T' + aula.horaInicio);
                    aula.horaFim = new Date('1970-01-01T' + aula.horaFim);
                });
            });
            this.horarios1 = horarios;
            this.tipoAtorGrid = tipoAtorBusca;
            this.horarioRelatorio = true;
        });
    }

    private construirFiltro(tipoAtorBusca: TipoAtorBuscaEnum) {
        this.horarioFiltroModel.tipoAtorBusca = tipoAtorBusca;
        this.horarioFiltroModel.matricula = this.form.get('matricula').value;
        this.horarioFiltroModel.rfId = this.form.get('rfId').value;
        if (tipoAtorBusca == TipoAtorBuscaEnum.ALUNO || tipoAtorBusca == TipoAtorBuscaEnum.PROFESSOR) {
            this.horarioFiltroModel.idTurma = 0;
        } else {
            this.horarioFiltroModel.idTurma = this.form.get('idTurma').value;
        }
    }

    onKey(event: any) {
        this.matricula = event.target.value;
    }

    private definirFormulario() {
        this.form = this.fb.group({
            matricula: [''],
            rfId: [null],
            idTurma: [0],
        });
    }

    limparAbas() {
        this.tipoAtorGrid = null;
        this.form.reset();
    }

    resetRfId() {
        this.form.get('rfId').reset();
    }

    gerarRelatorioAulas() {
        this.printReceipt();
    }

    printReceipt() {
        const printContent = document.getElementById('content-relatorio')?.innerHTML;
        const printWindow = window.open('', '', 'height=400,width=800');
        printWindow?.document.write('<html><head><title>Recibo</title>');
        printWindow?.document.write('</head><body>');
        printWindow?.document.write(printContent || '');
        printWindow?.document.write('</body></html>');
        printWindow?.document.close();
        printWindow?.print();
    }

}
