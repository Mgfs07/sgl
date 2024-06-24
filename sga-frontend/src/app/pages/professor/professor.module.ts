import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {ProfessorRoutingModule} from './professor-routing.module';
import {ProfessorComponent} from "./professor/professor.component";
import {ProfessorFormComponent} from "./professor/professor-form/professor-form.component";
import {SharedModule} from "../../shared/shared.module";


@NgModule({
    declarations: [ProfessorComponent, ProfessorFormComponent],
    imports: [
        CommonModule,
        ProfessorRoutingModule,
        SharedModule
    ]
})
export class ProfessorModule {
}
