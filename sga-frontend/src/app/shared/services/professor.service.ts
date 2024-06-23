import {Injectable} from '@angular/core';
import {AbstractService} from "./abstract.service";
import {HttpClient} from "@angular/common/http";
import {ProfessorModel} from "../models/professor.model";
import {ProfessorListModel} from "../models/professor-list.model";
import {Observable} from "rxjs";
import {SelectItem} from "primeng/api";
import {DropdownString} from "../util/dropdownString-util";

@Injectable({
    providedIn: 'root'
})
export class ProfessorService extends AbstractService<ProfessorModel, ProfessorListModel> {

    constructor(private httpClient: HttpClient) {
        super(httpClient);
    }

    override getEntity(): string {
        return "professores";
    }

    buscarDropdownProfessoresEcoordenadores(): Observable<DropdownString[]> {
        return this.http.get<DropdownString[]>(this.resourceUrl + '/dropdown')
    }

}
