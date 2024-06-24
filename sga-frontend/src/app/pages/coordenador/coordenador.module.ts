import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CoordenadorRoutingModule } from './coordenador-routing.module';
import {CoordenadorFormComponent} from "./coordenador/coordenador-form/coordenador-form.component";
import {CoordenadorComponent} from "./coordenador/coordenador.component";


@NgModule({
  declarations: [CoordenadorFormComponent, CoordenadorComponent],
  imports: [
    CommonModule,
    CoordenadorRoutingModule
  ]
})
export class CoordenadorModule { }
