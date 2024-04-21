import {Injectable} from '@angular/core';
import {AbstractService} from "./abstract.service";
import {HttpClient} from "@angular/common/http";
import {DisciplinaModel} from "../models/disciplina.model";
import {DisciplinaListModel} from "../models/disciplina-list.model";
import {Observable} from "rxjs";
import {SelectItem} from "../models/select-item";
import {CoordenadorModel} from "../models/coordenador.model";
import {CoordenadorListModel} from "../models/coordenador-list.model";

@Injectable({
    providedIn: 'root'
})
export class CoordenadorService extends AbstractService<CoordenadorModel, CoordenadorListModel> {

    constructor(private httpClient: HttpClient) {
        super(httpClient);
    }

    override getEntity(): string {
        return "coordenadores";
    }

}
