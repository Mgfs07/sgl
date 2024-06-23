import {AbstractService} from "./abstract.service";
import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {AuditoriaEventoModel} from "../models/auditoria-evento.model";
import {Observable} from "rxjs";
import {SelectItem} from "primeng/api";

@Injectable({
    providedIn: 'root'
})
export class AuditoriaService extends AbstractService<any, AuditoriaEventoModel> {

    constructor(private httpClient: HttpClient) {
        super(httpClient);
    }

    override getEntity(): string {
        return "auditoria";
    }

    buscarTodasAuditoriaEvento(): Observable<AuditoriaEventoModel[]> {
        return this.http.get<AuditoriaEventoModel[]>(this.resourceUrl + '/evento')
    }
}
