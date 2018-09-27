import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { LoginComponent } from './login.component';
import { ModalModule } from 'ngx-bootstrap/modal';

export const routes = [
    { path: '', component: LoginComponent, pathMatch: 'full' }
];

@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        ReactiveFormsModule,
        RouterModule.forChild(routes),
        ModalModule.forRoot()
    ],
    declarations: [LoginComponent]
})

export class LoginModule { }