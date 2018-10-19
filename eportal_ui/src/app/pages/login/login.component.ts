import { Component, OnInit, ViewEncapsulation, ViewChild } from '@angular/core';
import { HTTP, Rsa } from 'app/utilities';
import { UserProvider, } from 'app/providers';
import { Router } from '@angular/router';
import { FormGroup, AbstractControl, FormBuilder, Validators } from '@angular/forms';
import { CustomValidators } from 'ng2-validation';
import { ModalDirective } from 'ngx-bootstrap';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss'],
    encapsulation: ViewEncapsulation.None
})

export class LoginComponent implements OnInit {
    public vm: any = { email: "", userName: "", password: "" }
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
    public loader = false;
    public isShow = true;
    public pwdPattern = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$$";
    public message = "";
    public showMsg = false;

    @ViewChild('forgotPassModal') public forgotPassModal: ModalDirective;
    @ViewChild('notify') public notify: ModalDirective;

    constructor(router: Router, fb: FormBuilder, private pro: UserProvider, private rsa: Rsa) {
        this.router = router;
        this.form = fb.group({
            'email': ['', Validators.compose([Validators.required, CustomValidators.email])],
            'password': ['', Validators.compose([Validators.required, Validators.minLength(6)])]
        });

        this.email = this.form.controls['email'];
        this.password = this.form.controls['password'];
    }

    ngOnInit() {
        this.getConfig();
    }

    public showForgotPasss() {
        this.forgotPassModal.show();
        this.vm.email = "";
        this.isShow = false;
        this.isFEmail = false;
        this.isFormatEmail = false;
    }

    public onSubmit(values: Object): void {
        if (this.form.valid) {
            this.router.navigate(['pages/dashboard']);
        }
    }

    ngAfterViewInit() {
        document.getElementById('preloader').style.display = 'none';
    }

    public signIn() {
        document.getElementById('preloader').style.display = 'block';

        this.loader = true;
        this.showMsg = false;
        let obj = {
            email: this.vm.userName,
            password: this.vm.password
        };

        this.pro.signIn(obj).subscribe((rsp: any) => {
            if (rsp.status === HTTP.STATUS_SUCCESS) {
                this.message = "";
                this.pro.saveAuth(rsp.result);
            } else {
                this.showMsg = true;
                this.message = " Invalid User ID or Password";
            }
            this.loader = false;
        }, err => console.log(err));

        setTimeout(function () {
            document.getElementById('preloader').style.display = 'none';
        }, 500);
    }

    public sendEmailVerificationLink() {
        var EMAIL_REGEXP = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;

        if (this.vm.email == "") {
            this.isFEmail = true;
            this.isFormatEmail = false;
            this.isExistingFEmail = false;
            return;
        }
        if (!EMAIL_REGEXP.test(this.vm.email)) {
            this.isFormatEmail = true;
            this.isFEmail = false;
            this.isExistingFEmail = false;
            return;
        }

        document.getElementById('preloader').style.display = 'block';

        let obj = { keyword: this.vm.email };
        this.pro.updateToken(obj).subscribe((rsp: any) => {
            if (rsp.status === HTTP.STATUS_SUCCESS) {
                this.notify.show();
                this.forgotPassModal.hide();
            } else {
                this.isExistingFEmail = true;
                this.isFormatEmail = false;
                this.isFEmail = false;
                this.messageForgot = rsp.message;
            }
        });

        setTimeout(function () {
            document.getElementById('preloader').style.display = 'none';
        }, 500);
    }

    private getConfig() {
        this.pro.getConfig().subscribe((rsp: any) => {
            if (rsp.status === HTTP.STATUS_SUCCESS) {
                this.rsa.enabled = rsp.result.RSA_MODE;
                this.rsa.publicKey = rsp.result.RSA_PUBLIC_KEY;
            }
            else {
                this.rsa.enabled = false;
            }
        }, (err) => { console.log(err); });
    }

    public cancelClick() {
        this.forgotPassModal.hide();
        this.isShow = true;
        this.showMsg = false;
        this.vm.email = "";
        this.vm.userName = "";
        this.vm.password = "";
    }
}