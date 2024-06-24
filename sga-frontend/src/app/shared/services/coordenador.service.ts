import {Injectable} from '@angular/core';
import {AbstractService} from "./abstract.service";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
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

    deletePorMatricula(id: string): Observable<void> {
        return this.http.delete<void>(this.resourceUrl + '/' + id);
    }


}
