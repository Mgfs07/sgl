import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {AbstractService} from "./abstract.service";
import {HorariosAlunoModel} from "../models/horarios-aluno.model";
import {Observable} from "rxjs";
import { HorarioModel } from 'src/app/shared/components/grid-horario/horario.model';
import {SelectItem} from "../models/select-item";
import {HorarioFiltroModel} from "../models/horario-filtro.model";
import {AulaModel} from "../components/grid-horario/aula.model";
import {AulaAlocacaoListModel} from "../models/aula-alocacao-list.model";
import {AulaAlocacaoModel} from "../models/aula-alocacao.model";
import {AlocacaoLocalAulaModel} from "../models/alocacao-local-aula.model";

@Injectable({
  providedIn: 'root'
})
export class AulaService extends AbstractService<AulaAlocacaoModel, AulaAlocacaoListModel>{

    constructor(private httpClient: HttpClient) {
        super(httpClient);
    }

    override getEntity(): string {
        return "aulas";
    }

    buscarAulasDisponiveis(idDisciplina: number): Observable<AulaAlocacaoListModel[]> {
        let params: HttpParams = new HttpParams();
        if (idDisciplina) {
            params = params.set('idDisciplina', idDisciplina);
        }
        return this.http.get<AulaAlocacaoListModel[]>(this.resourceUrl + '/alocacao', {params});
    }

    alocarAula(alocacaoLocalAula: AlocacaoLocalAulaModel): Observable<void> {
        return this.http.post<void>('/api/aulas/alocar-local', alocacaoLocalAula);
    }

    buscarHorario(filtro: HorarioFiltroModel): Observable<HorarioModel[]>{
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
