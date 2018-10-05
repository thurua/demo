import { Component, ViewEncapsulation, ViewChild, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { FormGroup, AbstractControl, FormBuilder, Validators } from '@angular/forms';
import { CustomValidators } from 'ng2-validation';
import { UserProvider } from '../../providers/provider';
import { ModalDirective } from 'ngx-bootstrap';
import { HTTP } from '../../utilities/utility';

@Component({
    selector: 'app-login',
    templateUrl: './reset-password.component.html',
    styleUrls: ['./reset-password.component.scss'],
    encapsulation: ViewEncapsulation.None
})
export class ResetPasswordComponent implements OnInit {
    public vm: any = { email: "" }
    public form: FormGroup;
    public email: AbstractControl;
    public password: AbstractControl;
    public txtForgotEmail = "";
    public isExistingFEmail = false;
    public isFEmail = false;
    public isFormatEmail = false;
    public messageForgot = '';
    public msgExistingAccount = '';
    public pwdPattern = "((?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*\W).{8})";
    public type = "password";
    public typeConfirm = "password";
    public show = false;
    public showConfirm = false;
    public typePassLogin = "password";
    public showPassLogin = false;
    public isOneSpecialChar = false;
    public token = '';
    public title = '';
    public message = '';
    public isShow = false;

    @ViewChild('ChangePassword') public ChangePassword: ModalDirective;
    @ViewChild('notify') public notify: ModalDirective;

    constructor(private router: Router, fb: FormBuilder, private pro: UserProvider, private act: ActivatedRoute) {
        this.form = fb.group({
            'email': ['', Validators.compose([Validators.required, CustomValidators.email])],
            'password': ['', Validators.compose([Validators.required, Validators.minLength(6)])]
        });

        this.email = this.form.controls['email'];
        this.password = this.form.controls['password'];

        this.act.params.subscribe((params: Params) => {
            this.token = params["token"];
            let mode = this.token.substring(0, 1);

            if (mode == 'S' || mode == 'R') {
                this.title = 'Password Setup';
            } else if (mode == 'A') {
                this.title = 'Reset Password';
            }
        });
    }

    ngOnInit() {
        this.vm.Password = null;
        this.vm.confirmPassword = null;
        this.checkExpired('Load');
    }

    public onSubmit(values: Object): void {
        if (this.form.valid) {
            this.router.navigate(['pages/dashboard']);
        }
    }

    ngAfterViewInit() {
        document.getElementById('preloader').classList.add('hide');
    }

    public toggleShow(para?: string) {
        this.show = !this.show;
        this.type = this.show ? "text" : "password";
    }

    public changePassword() {
        this.vm.email = this.token;

        let obj = { email: this.vm.email, newPassword: this.vm.password };
        this.pro.resetPassword(obj).subscribe((rsp: any) => {
            if (rsp.status === HTTP.STATUS_SUCCESS) {
                this.message = "";
                this.pro.saveAuth(rsp.result); // save JWT
                this.router.navigate(['pages/dashboard']);
            } else {
                let msg = rsp.message;
            }
        });
    }

    public checkExpired(str: String) {
        let obj = { keyword: this.token };

        this.pro.checkExpired(obj).subscribe((rsp: any) => {
            if (rsp.status === HTTP.STATUS_SUCCESS) {
                if (str == "Submit") {
                    this.changePassword();
                }
                else {
                    this.isShow = true;
                }
            } else {
                this.notify.show();
                this.isShow = false;
                this.message = "Password is expired. Please contact Salesforce Administrator";
            }
        });
    }
}