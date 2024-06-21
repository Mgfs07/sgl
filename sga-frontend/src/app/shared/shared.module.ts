import {NgModule} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {PRIMENG_IMPORTS} from "./primeng-imports";
import { StatusPipe } from './pipes/status.pipe';
import {CurrencyPipe, DatePipe, NgForOf, NgIf} from "@angular/common";


@NgModule({
    declarations: [
        StatusPipe,
    ],
    imports: [
        PRIMENG_IMPORTS,
        ReactiveFormsModule,
        FormsModule,
        DatePipe,
        CurrencyPipe,
        NgForOf,
        NgIf,
    ],
    exports: [
        PRIMENG_IMPORTS,
        FormsModule,
        StatusPipe,
        ReactiveFormsModule
    ]
})
export class SharedModule { }
