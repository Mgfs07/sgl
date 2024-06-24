import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {CoordenadorRoutingModule} from './coordenador-routing.module';
import {CoordenadorFormComponent} from "./coordenador/coordenador-form/coordenador-form.component";
import {CoordenadorComponent} from "./coordenador/coordenador.component";
import {SharedModule} from "../../shared/shared.module";


@NgModule({
    declarations: [CoordenadorFormComponent, CoordenadorComponent],
    imports: [
        CommonModule,
        CoordenadorRoutingModule,
        SharedModule
    ]
})
export class CoordenadorModule {
}
