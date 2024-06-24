import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {AlunoRoutingModule} from './aluno-routing.module';
import {AlunoComponent} from "./aluno/aluno.component";
import {AlunoFormComponent} from "./aluno/aluno-form/aluno-form.component";
import {SharedModule} from "../../shared/shared.module";


@NgModule({
    declarations: [AlunoComponent, AlunoFormComponent],
    imports: [
        CommonModule,
        AlunoRoutingModule,
        SharedModule
    ]
})
export class AlunoModule {
}
