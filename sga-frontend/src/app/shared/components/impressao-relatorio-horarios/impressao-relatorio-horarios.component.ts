import {Component, Input, OnInit} from '@angular/core';
import {AulaModel} from "../grid-horario/aula.model";
import {HorarioModel} from "../grid-horario/horario.model";
import {DialogService, DynamicDialogConfig, DynamicDialogRef} from "primeng/dynamicdialog";
import {ConfirmationService, MessageService} from "primeng/api";

@Component({
    selector: 'app-impressao-relatorio-horarios',
    templateUrl: './impressao-relatorio-horarios.component.html',
    styleUrls: ['./impressao-relatorio-horarios.component.scss']
})
export class ImpressaoRelatorioHorariosComponent implements OnInit {

    @Input() horarios1: HorarioModel[];
    aulasPorDia: { [key: number]: AulaModel[] } = {};


    constructor() {
    }

    ngOnInit() {
        this.organizarAulasPorDia()
    }


    organizarAulasPorDia() {
        for (let h of this.horarios1) {
            for (let aula of h.aulas) {
                if (!this.aulasPorDia[aula.diaSemana]) {
                    this.aulasPorDia[aula.diaSemana] = [];
                }
                this.aulasPorDia[aula.diaSemana].push(aula);
            }
        }
    }

    getDiaSemana(dia: number): string {
        const dias = [
            'Domingo', 'Segunda-Feira', 'Terça-Feira',
            'Quarta-Feira', 'Quinta-Feira', 'Sexta-Feira', 'Sábado'
        ];
        return dias[dia - 1];
    }




}

