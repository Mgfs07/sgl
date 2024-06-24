import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { EquipamentoRoutingModule } from './equipamento-routing.module';
import {EquipamentoComponent} from "./equipamento/equipamento.component";
import {EquipamentoFormComponent} from "./equipamento/equipamento-form/equipamento-form.component";


@NgModule({
  declarations: [EquipamentoComponent, EquipamentoFormComponent],
  imports: [
    CommonModule,
    EquipamentoRoutingModule
  ]
})
export class EquipamentoModule { }
