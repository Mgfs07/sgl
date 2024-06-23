import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {AbstractService} from "./abstract.service";
import {HorariosAlunoModel} from "../models/horarios-aluno.model";
import {Observable} from "rxjs";
import { HorarioModel } from 'src/app/shared/components/grid-horario/horario.model';
import {SelectItem} from "../models/select-item";
import {HorarioFiltroModel} from "../models/horario-filtro.model";

@Injectable({
  providedIn: 'root'
})
export class HorariosAlunoService extends AbstractService<HorariosAlunoModel, HorariosAlunoModel>{

    constructor(private httpClient: HttpClient) {
        super(httpClient);
    }

    override getEntity(): string {
        return "horarios";
    }

    buscarHorariosPorMatricula(matricula: string): Observable<HorarioModel[]> {
        return this.http.get<HorarioModel[]>(this.resourceUrl + '/horarios-aluno/' + matricula);
    }

    buscarHorarios(horarioFiltroModel: HorarioFiltroModel): Observable<HorarioModel[]> {
        return this.http.get<HorarioModel[]>('/api/horarios');
    }

    buscarDropdownTurmas(): Observable<SelectItem[]> {
        return this.http.get<SelectItem[]>(this.resourceUrl + '/dropdown/turma');
    }

    buscarHorariosTeste(filtro: HorarioFiltroModel): Observable<HorarioModel[]>{
        let params = new HttpParams();
        if (filtro.tipoAtorBusca) {
            params = params.set('tipoAtorBusca', filtro.tipoAtorBusca)
        }
        if (filtro.rfId) {
            params = params.set('rfId', filtro.rfId)
        }
        if (filtro.matricula) {
            params = params.set('matricula', filtro.matricula)
        }
        if (filtro.idTurma) {
            params = params.set('idTurma', filtro.idTurma)
        }
        return this.http.get<HorarioModel[]>('/api/horarios/horario', {params});
    }

}
