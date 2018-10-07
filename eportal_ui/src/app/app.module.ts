import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { routing } from './app.routing';
import { AppSettings } from './app.settings';
import { AppComponent } from './app.component';
import { NotFoundComponent } from './pages/errors/not-found/not-found.component';

import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

// Import providers
import {
    ApiProvider,
    UserProvider,
    FileProvider,
    CommonProvider,
    ScheduleProvider,
    CreditNoteProvider,
    InvoiceProvider,
    ReasonProvider
} from './providers/provider';

// Import utilities
import { RsaService, Utils } from './utilities/utility';

@NgModule({
    declarations: [
        AppComponent,
        NotFoundComponent
    ],
    imports: [
        BrowserModule,
        routing,
        HttpClientModule,
        FormsModule,
    ],
    providers: [
        AppSettings,
        ApiProvider,
        UserProvider,
        FileProvider,
        CommonProvider,
        RsaService,
        ScheduleProvider,
        CreditNoteProvider,
        Utils,
        InvoiceProvider,
        ReasonProvider
    ],
    bootstrap: [AppComponent]
})

export class AppModule { }