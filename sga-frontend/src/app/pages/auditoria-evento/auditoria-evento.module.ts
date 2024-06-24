import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AuditoriaEventoRoutingModule } from './auditoria-evento-routing.module';
import {AuditoriaEventoComponent} from "./auditoria-evento/auditoria-evento.component";
import {
    AuditoriaEventoModalComponent
} from "./auditoria-evento/auditoria-evento-modal/auditoria-evento-modal.component";


@NgModule({
  declarations: [
      AuditoriaEventoComponent,
      AuditoriaEventoModalComponent
  ],
  imports: [
    CommonModule,
    AuditoriaEventoRoutingModule
  ]
})
export class AuditoriaEventoModule { }
