import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {DisciplinaRoutingModule} from './disciplina-routing.module';
import {DisciplinaComponent} from "./disciplina/disciplina.component";
import {DisciplinaFormComponent} from "./disciplina/disciplina-form/disciplina-form.component";
import {SharedModule} from "../../shared/shared.module";


@NgModule({
    declarations: [DisciplinaComponent, DisciplinaFormComponent],
    imports: [
        CommonModule,
        DisciplinaRoutingModule,
        SharedModule
    ]
})
export class DisciplinaModule {
}
