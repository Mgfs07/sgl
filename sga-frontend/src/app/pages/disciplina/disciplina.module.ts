import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { DisciplinaRoutingModule } from './disciplina-routing.module';
import {DisciplinaComponent} from "./disciplina/disciplina.component";
import {DisciplinaFormComponent} from "./disciplina/disciplina-form/disciplina-form.component";


@NgModule({
  declarations: [DisciplinaComponent, DisciplinaFormComponent],
  imports: [
    CommonModule,
    DisciplinaRoutingModule
  ]
})
export class DisciplinaModule { }
