import {NgModule} from '@angular/core';
import {HashLocationStrategy, LocationStrategy, NgForOf} from '@angular/common';
import {AppComponent} from './app.component';
import {AppRoutingModule} from './app-routing.module';
import {AppLayoutModule} from './layout/app.layout.module';
import {TableModule} from "primeng/table";
import {SharedModule} from "./shared/shared.module";
import {ConfirmationService, MessageService} from "primeng/api";
import {ReactiveFormsModule} from "@angular/forms";
import {DialogService} from "primeng/dynamicdialog";
import { BlockUIModule } from 'ng-block-ui';
import {LoginService} from "./pages/login/login.service";
import {HTTP_INTERCEPTORS} from "@angular/common/http";
import {TokenInterceptor} from "./shared/interceptor/token-interceptor";

@NgModule({
    declarations: [
        AppComponent
    ],
    imports: [
        AppRoutingModule,
        AppLayoutModule,
        TableModule,
        SharedModule,
        ReactiveFormsModule,
        NgForOf,
        BlockUIModule.forRoot()
    ],
    providers: [
        MessageService, ConfirmationService, DialogService,LoginService,
        {
            provide: HTTP_INTERCEPTORS,
            useClass: TokenInterceptor,
            multi: true
        },
        {provide: LocationStrategy, useClass: HashLocationStrategy},
    ],
    exports: [],
    bootstrap: [AppComponent]
})
export class AppModule {
}
