import {Component} from '@angular/core';
import {Column} from "../../../shared/models/colum.model";
import {DialogService, DynamicDialogRef} from "primeng/dynamicdialog";
import {ConfirmationService, MessageService} from "primeng/api";
import {HorariosAlunoService} from "../../../shared/services/horarios-aluno.service";
import {HorariosAlunoModel} from "../../../shared/models/horarios-aluno.model";
import { BlockUI, NgBlockUI } from 'ng-block-ui';
import {finalize} from "rxjs";
import { HorarioModel } from './horario.model';
import { DiaSemanaEnum } from './dia-semana.enum';
import { AulaModel } from './aula.model';
import { TurnoModel } from './turno.model';
import { VagaAula } from './vaga-aula.model';

@Component({
  selector: 'app-horarios-aluno',
  templateUrl: './horarios-aluno.component.html',
  styleUrls: ['./horarios-aluno.component.scss']
})
export class HorariosAlunoComponent {

    @BlockUI() blockUI: NgBlockUI;
    cols!: Column[];
    horarios: HorariosAlunoModel[];
    ref: DynamicDialogRef | undefined;
    display: boolean = false;
    matricula: string;

    horarios1: HorarioModel[] = this.inicializarHorario();
    diasDaSemana: DiaSemanaEnum[] = DiaSemanaEnum.TODOS;
    turnos: TurnoModel[] = this.inicializarTurnos();


    constructor(public dialogService: DialogService,
                private messageService: MessageService,
                private service: HorariosAlunoService,
                private confirmationService: ConfirmationService) {
    }

    ngOnInit() {
        this.construirColunasListagem();
        this.service.buscarHorarios().subscribe((horarios: HorarioModel[]) => {
            horarios.forEach((horario: HorarioModel) => {
                horario.aulas.forEach((aula: AulaModel) => {
                    aula.horaInicio = new Date('1970-01-01T' + aula.horaInicio);
                    aula.horaFim = new Date('1970-01-01T' + aula.horaFim);
                    aula.local = 'LAB9'
                });
            });
            this.horarios1 = horarios;
        });
    }

    private construirColunasListagem() {
        this.cols = [
            {field: 'nomeAula', header: 'Disciplina', text: true},
            {field: 'professor', header: 'Professor', text: true},
            {field: 'local', header: 'Local'},
            {field: 'horaInicio', header: 'Hora Início', text: true},
            {field: 'horaFim', header: 'Hora Fim', text: true},
            {field: 'diaSemana', header: 'Dia da semana', text: true}

        ];
    }

    buscarHorarios() {
        this.blockUI.start("Carregando...");
        this.service.buscarHorariosPorMatricula(this.matricula)
            .pipe(finalize(() => this.blockUI.stop()))
            .subscribe((value) => {
            this.horarios = value;
        })
    }

    onKey(event: any) {
        this.matricula = event.target.value;
    }

    public obterDiasSemana(aulas: AulaModel[]): DiaSemanaEnum[] {
        return [
            DiaSemanaEnum.SEGUNDA,
            DiaSemanaEnum.TERCA,
            DiaSemanaEnum.QUARTA,
            DiaSemanaEnum.QUINTA,
            DiaSemanaEnum.SEXTA
        ].concat(this.aulaNoSabado(aulas) ? [DiaSemanaEnum.SABADO] : []);
    }

    public obterAula(vaga: VagaAula, diaSemana: number, aulas: AulaModel[]): AulaModel | undefined {
        return aulas.find((aula: AulaModel) => aula.horaInicio.getTime() == vaga.horaInicio.getTime() && diaSemana == aula.diaSemana);
    }

    public obterLegenda(horario: HorarioModel): string[] {
        let legenda: Map<string, string> = new Map();
        let itensLegenda: string[] = [];
        horario.aulas.forEach((aula: AulaModel) => legenda.set(aula.nomeAula, aula.nomeCompletoDisciplina));
        legenda.forEach((disciplina: string, abreviatura: string) => itensLegenda.push(abreviatura + ': ' + disciplina));
        return itensLegenda;
    }

    private aulaNoSabado(aulas: AulaModel[]): boolean {
        return aulas.some((aula: AulaModel) => aula.diaSemana == DiaSemanaEnum.SABADO.getId());
    }

    private inicializarHorario(): HorarioModel[] {
        return [
            new HorarioModel('Victor Barcelos Lacerda', 'COORD. DE SISTEMAS DE INFORMAÇÃO', '2024/1', [
                new AulaModel(1, 'SO', 'Sistemas Operacionais', 'Vanderson', horario('13:00'), horario('13:50'), 2, 'LAB5'),
                new AulaModel(2, 'SO', 'Sistemas Operacionais', 'Vanderson', horario('13:50'), horario('14:40'), 2, 'LAB5'),
                new AulaModel(3, 'SO', 'Sistemas Operacionais', 'Vanderson', horario('14:40'), horario('15:30'), 2, 'LAB5'),
                new AulaModel(4, 'SO', 'Sistemas Operacionais', 'Vanderson', horario('15:50'), horario('16:40'), 2, 'LAB5'),
                new AulaModel(5, 'SO', 'Sistemas Operacionais', 'Vanderson', horario('16:40'), horario('17:30'), 2, 'LAB5'),
                new AulaModel(6, 'SO', 'Sistemas Operacionais', 'Vanderson', horario('17:30'), horario('17:20'), 2, 'LAB5'),
                new AulaModel(6, 'SO', 'Sistemas Operacionais', 'Vanderson', horario('17:30'), horario('17:20'), 7, 'LAB5'),
            ])
        ];
    }

    private inicializarTurnos(): TurnoModel[] {
        return [
            new TurnoModel('MATUTINO', [
                new VagaAula(horario('07:00'), horario('07:50')),
                new VagaAula(horario('07:50'), horario('08:40')),
                new VagaAula(horario('08:40'), horario('09:30')),
                new VagaAula(horario('09:50'), horario('10:40')),
                new VagaAula(horario('10:40'), horario('11:30')),
                new VagaAula(horario('11:30'), horario('12:20'))
            ]),
            new TurnoModel('VESPERTINO', [
                new VagaAula(horario('13:00'), horario('13:50')),
                new VagaAula(horario('13:50'), horario('14:40')),
                new VagaAula(horario('14:40'), horario('15:30')),
                new VagaAula(horario('15:50'), horario('16:40')),
                new VagaAula(horario('16:40'), horario('17:30')),
                new VagaAula(horario('17:30'), horario('17:20'))
            ]),
            new TurnoModel('NOTURNO', [
                new VagaAula(horario('18:50'), horario('19:35')),
                new VagaAula(horario('19:35'), horario('20:20')),
                new VagaAula(horario('20:30'), horario('21:15')),
                new VagaAula(horario('21:15'), horario('22:00'))
            ]),
        ];
    }

}

function horario(hora: string): Date {
    return new Date('1970-01-01T' + hora);
}
