import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PeriodoRoutingModule } from './periodo-routing.module';
import {PeriodoComponent} from "./periodo/periodo.component";
import {PeriodoFormComponent} from "./periodo/periodo-form/periodo-form.component";


@NgModule({
  declarations: [PeriodoComponent, PeriodoFormComponent],
  imports: [
    CommonModule,
    PeriodoRoutingModule
  ]
})
export class PeriodoModule { }
