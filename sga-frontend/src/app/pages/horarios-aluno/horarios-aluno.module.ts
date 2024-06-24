import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {HorariosAlunoRoutingModule} from './horarios-aluno-routing.module';
import {HorariosAlunoComponent} from "./horarios-aluno/horarios-aluno.component";
import {SharedModule} from "../../shared/shared.module";


@NgModule({
    declarations: [HorariosAlunoComponent],
    imports: [
        CommonModule,
        HorariosAlunoRoutingModule,
        SharedModule
    ]
})
export class HorariosAlunoModule {
}
