import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {EquipamentoRoutingModule} from './equipamento-routing.module';
import {EquipamentoComponent} from "./equipamento/equipamento.component";
import {EquipamentoFormComponent} from "./equipamento/equipamento-form/equipamento-form.component";
import {SharedModule} from "../../shared/shared.module";


@NgModule({
    declarations: [EquipamentoComponent, EquipamentoFormComponent],
    imports: [
        CommonModule,
        EquipamentoRoutingModule,
        SharedModule
    ]
})
export class EquipamentoModule {
}
