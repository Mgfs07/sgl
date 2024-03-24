import {NgModule} from '@angular/core';
import {HashLocationStrategy, LocationStrategy} from '@angular/common';
import {AppComponent} from './app.component';
import {AppRoutingModule} from './app-routing.module';
import {AppLayoutModule} from './layout/app.layout.module';
import {TableModule} from "primeng/table";
import {SharedModule} from "./shared/shared.module";
import {ConfirmationService, MessageService} from "primeng/api";
import {ReactiveFormsModule} from "@angular/forms";

@NgModule({
    declarations: [
        AppComponent,
    ],
    imports: [
        AppRoutingModule,
        AppLayoutModule,
        TableModule,
        SharedModule,
        ReactiveFormsModule],
    providers: [
        MessageService, ConfirmationService,
        {provide: LocationStrategy, useClass: HashLocationStrategy},
    ],
    exports: [],
    bootstrap: [AppComponent]
})
export class AppModule {
}
