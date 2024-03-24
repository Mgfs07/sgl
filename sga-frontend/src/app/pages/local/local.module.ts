import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { LocalRoutingModule } from './local-routing.module';
import { EquipamentoComponent } from './equipamento/equipamento.component';
import { EquipamentoFormComponent } from './equipamento/equipamento-form/equipamento-form.component';
import {ButtonModule} from "primeng/button";
import {ConfirmDialogModule} from "primeng/confirmdialog";
import {SharedModule} from "primeng/api";
import {TableModule} from "primeng/table";
import {ToastModule} from "primeng/toast";
import {InputTextModule} from "primeng/inputtext";
import {PaginatorModule} from "primeng/paginator";
import {ReactiveFormsModule} from "@angular/forms";
import { DisciplinaComponent } from './disciplina/disciplina.component';
import { DisciplinaFormComponent } from './disciplina/disciplina-form/disciplina-form.component';
import { CoordenadoriaComponent } from './coordenadoria/coordenadoria.component';
import { CoordenadoriaFormComponent } from './coordenadoria/coordenadoria-form/coordenadoria-form.component';
import { AlunoComponent } from './aluno/aluno.component';
import { AlunoFormComponent } from './aluno/aluno-form/aluno-form.component';


@NgModule({
  declarations: [
    EquipamentoComponent,
    EquipamentoFormComponent,
    DisciplinaComponent,
    DisciplinaFormComponent,
    CoordenadoriaComponent,
    CoordenadoriaFormComponent,
    AlunoComponent,
    AlunoFormComponent
  ],
    imports: [
        CommonModule,
        LocalRoutingModule,
        ButtonModule,
        ConfirmDialogModule,
        SharedModule,
        TableModule,
        ToastModule,
        InputTextModule,
        PaginatorModule,
        ReactiveFormsModule
    ]
})
export class LocalModule { }
