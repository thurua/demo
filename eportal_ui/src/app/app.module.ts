import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { routing } from './app.routing';
import { AppSettings } from './app.settings';
import { AppComponent } from './app.component';
import { NotFoundComponent } from './pages/errors/not-found/not-found.component';

import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { FileUploadModule } from 'ng2-file-upload';

import { SignInComponent } from './views/sign-in/sign-in.component';
import { SignUpComponent } from './views/sign-up/sign-up.component';
import { FileComponent } from './views/file/file.component';
import { ProfileComponent } from './views/profile/profile.component';

// Import providers
import {
    ApiProvider,
    UserProvider,
    FileProvider,
    CommonProvider
} from './providers/provider';

// Import utilities
import { RsaService } from './utilities/utility';

@NgModule({
    declarations: [
        AppComponent,
        NotFoundComponent,
        SignInComponent,
        SignUpComponent,
        FileComponent,
        ProfileComponent
    ],
    imports: [
        BrowserModule,
        routing,
        HttpClientModule,
        FormsModule,
        FileUploadModule
    ],
    providers: [
        AppSettings,
        ApiProvider,
        UserProvider,
        FileProvider,
        CommonProvider,
        RsaService
    ],
    bootstrap: [AppComponent]
})

export class AppModule { }