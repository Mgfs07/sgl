import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {CoordenadorComponent} from "./coordenador/coordenador.component";

const routes: Routes =[
    {path: 'coordenadores', component: CoordenadorComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CoordenadorRoutingModule { }
