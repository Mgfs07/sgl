import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HorariosAlunoRoutingModule } from './horarios-aluno-routing.module';
import {HorariosAlunoComponent} from "./horarios-aluno/horarios-aluno.component";


@NgModule({
  declarations: [HorariosAlunoComponent],
  imports: [
    CommonModule,
    HorariosAlunoRoutingModule
  ]
})
export class HorariosAlunoModule { }
