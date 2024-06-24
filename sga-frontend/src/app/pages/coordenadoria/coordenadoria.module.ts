import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {CoordenadoriaRoutingModule} from './coordenadoria-routing.module';
import {CoordenadoriaComponent} from "./coordenadoria/coordenadoria.component";
import {CoordenadoriaFormComponent} from "./coordenadoria/coordenadoria-form/coordenadoria-form.component";
import {SharedModule} from "../../shared/shared.module";


@NgModule({
    declarations: [CoordenadoriaComponent, CoordenadoriaFormComponent],
    imports: [
        CommonModule,
        CoordenadoriaRoutingModule,
        SharedModule
    ]
})
export class CoordenadoriaModule {
}
