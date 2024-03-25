import {Injectable} from '@angular/core';
import {AbstractService} from "./abstract.service";
import {HttpClient} from "@angular/common/http";
import {DisciplinaModel} from "../models/disciplina.model";
import {DisciplinaListModel} from "../models/disciplina-list.model";
import {Observable} from "rxjs";
import {SelectItem} from "../models/select-item";

@Injectable({
    providedIn: 'root'
})
export class CoordenadoriaService extends AbstractService<DisciplinaModel, DisciplinaListModel> {

    constructor(private httpClient: HttpClient) {
        super(httpClient);
    }

    override getEntity(): string {
        return "coordenadorias";
    }

    buscarDropdown(): Observable<SelectItem[]> {
        return this.http.get<SelectItem[]>(this.resourceUrl + '/dropdown');
    }


}
