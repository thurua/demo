import { Component, ViewEncapsulation, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { FormGroup, FormControl, AbstractControl, FormBuilder, Validators } from '@angular/forms';
import { CustomValidators } from 'ng2-validation';
import { UserProvider, CommonProvider } from '../../providers/provider';
import { HTTP } from '../../utilities/utility';
import { ModalDirective } from 'ngx-bootstrap';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss'],
    encapsulation: ViewEncapsulation.None
})
export class LoginComponent {
    public vm: any = { email: "" }
    public router: Router;
    public form: FormGroup;
    public email: AbstractControl;
    public password: AbstractControl;
    public txtForgotEmail = "";
    public isExistingFEmail = false;
    public isFEmail = false;
    public isFormatEmail = false;
    public messageForgot = '';
    public msgExistingAccount = '';

    @ViewChild('forgotPassModal') public forgotPassModal: ModalDirective;
    @ViewChild('ForgotPassModel2') public ForgotPassModel2: ModalDirective;

    constructor(router: Router, fb: FormBuilder, private pro: UserProvider) {
        this.router = router;
        this.form = fb.group({
            'email': ['', Validators.compose([Validators.required, CustomValidators.email])],
            'password': ['', Validators.compose([Validators.required, Validators.minLength(6)])]
        });

        this.email = this.form.controls['email'];
        this.password = this.form.controls['password'];
    }

    public showForgotPasss() {
        this.forgotPassModal.show();
    }

    public onSubmit(values: Object): void {
        if (this.form.valid) {
            this.router.navigate(['pages/dashboard']);
        }
    }

    ngAfterViewInit() {
        document.getElementById('preloader').classList.add('hide');
    }

    public signIn() {
        //this.loader = true;

        let obj = {
            email: 'toan.nguyen@tanvieta.co',
            password: 'Qwerty123!'
        };

        this.pro.signIn(obj).subscribe((rsp: any) => {
            if (rsp.status === HTTP.STATUS_SUCCESS) {
                //this.message = "";
                //console.log(rsp);

                this.pro.saveAuth(rsp.result); // save JWT
                this.router.navigate(['pages/dashboard']);

            } else {
                //this.message = rsp.message;
            }

            //this.loader = false;
        }, err => console.log(err));
    }

    public sendEmailVerificationLink(valid: boolean) {
        if (!valid) {
            return;
        }

        //this.loader = true;
        let obj = { keyword: this.vm.email };
        this.pro.updateToken(obj).subscribe((rsp: any) => {
            if (rsp.status === HTTP.STATUS_SUCCESS) {
                alert("Please check mail box to change your password!");
            } else {
                let msg = rsp.message;
            }
            //this.loader = false;
            this.forgotPassModal.hide();
            //this.show = false;
        });
    }
}