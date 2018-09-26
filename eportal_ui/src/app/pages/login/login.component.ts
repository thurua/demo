import { Component, ViewEncapsulation } from '@angular/core';
import { Router } from '@angular/router';
import { FormGroup, FormControl, AbstractControl, FormBuilder, Validators } from '@angular/forms';
import { CustomValidators } from 'ng2-validation';
import { UserProvider, CommonProvider } from '../../providers/provider';
import { HTTP } from '../../utilities/utility';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss'],
    encapsulation: ViewEncapsulation.None
})
export class LoginComponent {
    public router: Router;
    public form: FormGroup;
    public email: AbstractControl;
    public password: AbstractControl;

    constructor(router: Router, fb: FormBuilder, private pro: UserProvider) {
        this.router = router;
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
}