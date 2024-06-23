import {Injectable} from '@angular/core';
import {AbstractService} from "./abstract.service";
import {HttpClient} from "@angular/common/http";
import {LocalModel} from "../models/local.model";
import {LocalListModel} from "../models/local-list.model";
import {Observable} from "rxjs";
import {SelectItem} from "primeng/api";

@Injectable({
    providedIn: 'root'
})
export class LocalService extends AbstractService<LocalModel, LocalListModel> {

    constructor(private httpClient: HttpClient) {
        super(httpClient);
    }

    override getEntity(): string {
        return "locais";
    }

    buscarDropdownLocais(): Observable<SelectItem[]> {
        return this.http.get<SelectItem[]>(this.resourceUrl + '/dropdown')
    }

    buscarDropdownLocalAlocacao(idAula: number): Observable<SelectItem[]> {
        return this.http.get<SelectItem[]>(this.resourceUrl + '/dropdown/' + idAula)
    }

}
