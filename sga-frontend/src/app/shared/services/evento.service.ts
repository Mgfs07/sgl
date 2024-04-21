import { Injectable } from '@angular/core';
import {AbstractService} from "./abstract.service";
import {EventoModel} from "../models/evento.model";
import {EventoListModel} from "../models/evento-list.model";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class EventoService extends AbstractService<EventoModel, EventoListModel> {

    constructor(private httpClient: HttpClient) {
        super(httpClient);
    }

    override getEntity(): string {
        return "eventos";
    }
}
