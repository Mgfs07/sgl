import {Injectable} from '@angular/core';
import {AbstractService} from "./abstract.service";
import {HttpClient} from "@angular/common/http";
import {EquipamentoModel} from "../models/equipamento.model";
import {EquipamentoListModel} from "../models/equipamento-list.model";
import {Observable} from "rxjs";
import {SelectItem} from "primeng/api";

@Injectable({
    providedIn: 'root'
})
export class EquipamentoService extends AbstractService<EquipamentoModel, EquipamentoListModel> {

    constructor(private httpClient: HttpClient) {
        super(httpClient);
    }

    override getEntity(): string {
        return "equipamentos";
    }

    buscarEquipamentosDropdown(): Observable<SelectItem[]> {
        return this.http.get<SelectItem[]>(this.resourceUrl + '/dropdown')
    }


}
