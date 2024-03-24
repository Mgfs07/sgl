import {Injectable} from '@angular/core';
import {AbstractService} from "./abstract.service";
import {HttpClient} from "@angular/common/http";
import {EquipamentoModel} from "../models/equipamento.model";
import {EquipamentoListModel} from "../models/equipamento-list.model";

@Injectable({
    providedIn: 'root'
})
export class EquipamentoService extends AbstractService<EquipamentoModel, EquipamentoListModel> {

    constructor(private httpClient: HttpClient) {
        super(httpClient);
    }

    override getEntity(): string {
        return "equipamento";
    }

}
