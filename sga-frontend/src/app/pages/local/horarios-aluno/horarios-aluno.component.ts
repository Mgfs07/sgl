import {Component} from '@angular/core';
import {Column} from "../../../shared/models/colum.model";
import {DialogService, DynamicDialogRef} from "primeng/dynamicdialog";
import {ConfirmationService, MessageService} from "primeng/api";
import {HorariosAlunoService} from "../../../shared/services/horarios-aluno.service";
import {HorariosAlunoModel} from "../../../shared/models/horarios-aluno.model";
import { BlockUI, NgBlockUI } from 'ng-block-ui';
import {finalize} from "rxjs";

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


    constructor(public dialogService: DialogService,
                private messageService: MessageService,
                private service: HorariosAlunoService,
                private confirmationService: ConfirmationService) {
    }

    ngOnInit() {
        this.construirColunasListagem();
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

}
