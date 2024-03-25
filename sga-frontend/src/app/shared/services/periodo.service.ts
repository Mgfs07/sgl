import {Injectable} from '@angular/core';
import {AbstractService} from "./abstract.service";
import {HttpClient} from "@angular/common/http";
import {DisciplinaModel} from "../models/disciplina.model";
import {DisciplinaListModel} from "../models/disciplina-list.model";
import {PeriodoModel} from "../models/periodo.model";
import {PeriodoListModel} from "../models/periodo-list.model";

@Injectable({
    providedIn: 'root'
})
export class PeriodoService extends AbstractService<PeriodoModel, PeriodoListModel> {

    constructor(private httpClient: HttpClient) {
        super(httpClient);
    }

    override getEntity(): string {
        return "periodos";
    }

}
