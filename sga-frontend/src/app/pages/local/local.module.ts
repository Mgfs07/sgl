import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {LocalRoutingModule} from './local-routing.module';
import {ButtonModule} from "primeng/button";
import {ConfirmDialogModule} from "primeng/confirmdialog";
import {TableModule} from "primeng/table";
import {ToastModule} from "primeng/toast";
import {InputTextModule} from "primeng/inputtext";
import {PaginatorModule} from "primeng/paginator";
import {ReactiveFormsModule} from "@angular/forms";
import {SharedModule} from "../../shared/shared.module";
import {LocalComponent} from './local/local.component';
import {LocalFormComponent} from './local/local-form/local-form.component';


@NgModule({
    declarations: [
        LocalComponent,
        LocalFormComponent,
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
export class LocalModule {
}
