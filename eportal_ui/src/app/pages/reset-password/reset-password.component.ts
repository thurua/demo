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
export class ResetPasswordComponent {
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
    public pwdPattern = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$$";
    public type = "password";
    public typeConfirm = "password";
    public show = false;
    public showConfirm = false;
    public typePassLogin = "password";
    public showPassLogin = false;

    @ViewChild('ChangePassword') public ChangePassword: ModalDirective;

    constructor(private router: Router, fb: FormBuilder, private pro: UserProvider, private act: ActivatedRoute) {
        this.form = fb.group({
            'email': ['', Validators.compose([Validators.required, CustomValidators.email])],
            'password': ['', Validators.compose([Validators.required, Validators.minLength(6)])]
        });

        this.email = this.form.controls['email'];
        this.password = this.form.controls['password'];
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
        if (para == 'P') {
            this.show = !this.show;
            this.type = this.show ? "text" : "password";
        }
        else if (para == 'PCF') {
            this.showConfirm = !this.showConfirm;
            this.typeConfirm = this.showConfirm ? "text" : "password";
        }
        else {
            this.showPassLogin = !this.showPassLogin;
            this.typePassLogin = this.showPassLogin ? "text" : "password";
        }
    }

    public changePassword(valid: boolean) {
        if (!valid) {
            return;
        }

        this.act.params.subscribe((params: Params) => {
            let token = params["token"];
            this.vm.email = token
        });

        let obj = { email: this.vm.email, newPassword: this.vm.password };
        this.pro.resetPassword(obj).subscribe((rsp: any) => {
            if (rsp.status === HTTP.STATUS_SUCCESS) {
                alert("Reset Password successful");
                this.router.navigate(['/']);
            } else {
                let msg = rsp.message;
            }
            //this.loader = false;
        });
    }
}