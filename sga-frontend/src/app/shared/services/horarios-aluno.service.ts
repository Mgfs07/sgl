import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {AbstractService} from "./abstract.service";
import {HorariosAlunoModel} from "../models/horarios-aluno.model";
import {Observable} from "rxjs";
import { HorarioModel } from 'src/app/pages/local/horarios-aluno/horario.model';

@Injectable({
  providedIn: 'root'
})
export class HorariosAlunoService extends AbstractService<HorariosAlunoModel, HorariosAlunoModel>{

    constructor(private httpClient: HttpClient) {
        super(httpClient);
    }

    override getEntity(): string {
        return "aulas";
    }

    buscarHorariosPorMatricula(matricula: string): Observable<HorariosAlunoModel[]> {
        return this.http.get<HorariosAlunoModel[]>(this.resourceUrl + '/horarios-aluno/' + matricula);
    }

    buscarHorarios(): Observable<HorarioModel[]> {
        return this.http.get<HorarioModel[]>(this.resourceUrl + '/horarios');
    }

}
