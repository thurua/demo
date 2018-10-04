import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ResetPasswordComponent } from './reset-password.component';
import { ModalModule } from 'ngx-bootstrap/modal';
import { EqualValidator } from './equal-validator.directive';

export const routes = [
    { path: '', component: ResetPasswordComponent, pathMatch: 'full' }
];

@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        ReactiveFormsModule,
        RouterModule.forChild(routes),
        ModalModule.forRoot()
    ],
    declarations: [ResetPasswordComponent,EqualValidator]
})

export class ResetPasswordModule { }