import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './/app-routing.module';
import { TopMenuComponent } from './views/shared/top-menu/top-menu.component';
import { BottomMenuComponent } from './views/shared/bottom-menu/bottom-menu.component';
import { HomeComponent } from './views/home/home.component';
import { ResSignupComponent } from './views/res-signup/res-signup.component';
import { ResWelcomeComponent } from './views/res-welcome/res-welcome.component';
import { BizSignupComponent } from './views/biz-signup/biz-signup.component';
import { BizWelcomeComponent } from './views/biz-welcome/biz-welcome.component';
import { ResPromoComponent } from './views/res-promo/res-promo.component';
import { Utils } from "./util";

import { FormsModule } from '@angular/forms';
import { ModalModule, BsDatepickerModule, TooltipModule } from 'ngx-bootstrap';

import {
    ApiProvider,
    SalesforceProvider
} from '../app/providers/providers';


@NgModule({
    declarations: [
        AppComponent,
        TopMenuComponent,
        BottomMenuComponent,
        HomeComponent,
        ResSignupComponent,
        ResWelcomeComponent,
        BizSignupComponent,
        BizWelcomeComponent,
        ResPromoComponent
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        HttpClientModule,
        FormsModule,
        ModalModule.forRoot(),
        BsDatepickerModule.forRoot(),
        TooltipModule.forRoot()
    ],
    providers: [
        ApiProvider,
        Utils,
        SalesforceProvider
    ],
    bootstrap: [AppComponent]
})

export class AppModule { }