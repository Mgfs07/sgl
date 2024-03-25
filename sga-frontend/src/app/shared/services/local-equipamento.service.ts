import {Injectable} from '@angular/core';
import {AbstractService} from "./abstract.service";
import {HttpClient} from "@angular/common/http";
import {LocalEquipamentoModel} from "../models/local-equipamento.model";
import {LocalEquipamentoListModel} from "../models/local-equipamento-list.model";
import {Observable} from "rxjs";
import {EquipamentoListModel} from "../models/equipamento-list.model";
import {SelectItem} from "primeng/api";

@Injectable({
    providedIn: 'root'
})
export class LocalEquipamentoService extends AbstractService<LocalEquipamentoModel, LocalEquipamentoListModel> {

    constructor(private httpClient: HttpClient) {
        super(httpClient);
    }

    override getEntity(): string {
        return "local-equipamento";
    }

    buscarEquipamentosListagemByIdLocal(idLocal: number): Observable<EquipamentoListModel[]> {
        return this.http.get<SelectItem[]>(this.resourceUrl + '/' + idLocal)
    }

    excluirEquipamentoListagem(idLocal: number, idEquipamento: number): Observable<void> {
        return this.http.delete<void>(this.resourceUrl + '/' + idLocal + '/' + idEquipamento);
    }

}
