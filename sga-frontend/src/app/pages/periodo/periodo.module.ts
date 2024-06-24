import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {PeriodoRoutingModule} from './periodo-routing.module';
import {PeriodoComponent} from "./periodo/periodo.component";
import {PeriodoFormComponent} from "./periodo/periodo-form/periodo-form.component";
import {SharedModule} from "../../shared/shared.module";


@NgModule({
    declarations: [PeriodoComponent, PeriodoFormComponent],
    imports: [
        CommonModule,
        PeriodoRoutingModule,
        SharedModule
    ]
})
export class PeriodoModule {
}
