import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {AlocacaoComponent} from "./alocacao/alocacao.component";

const routes: Routes = [{path: 'alocacao', component: AlocacaoComponent}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AlocacaoRoutingModule { }
