import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {CoordenadoriaComponent} from "./coordenadoria/coordenadoria.component";

const routes: Routes =[
    {path: 'coordenadorias', component: CoordenadoriaComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CoordenadoriaRoutingModule { }
