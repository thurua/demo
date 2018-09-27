import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { ApiProvider } from './api';
import { RsaService } from './../utilities/utility';

@Injectable()
export class UserProvider {
    public timerLogout;
    public subscriptionLogout;

    constructor(private api: ApiProvider,
        private rou: Router,
        private rsa: RsaService) { }

    /**
     * Sign in
     * @param info
     */
    public signIn(info: any) {
        //info.password = this.rsa.encrypt(info.password); // encrypt password
        return this.api.post('portal-user/sign-in', info);
    }

    /**
     * Sign up
     * @param info
     */
    public signUp(info: any) {
        //info.password = this.rsa.encrypt(info.password); // encrypt password
        return this.api.post('user/sign-up', info);
    }

    /**
     * Save user
     * @param info
     */
    public save(info: any) {
        return this.api.post('portal-user/save', info);
    }

    /**
     * View user
     */
    public view() {
        let x = {};
        return this.api.post('portal-user/view', x);
    }

    /**
     * Sign out
     */
    public signOut() {
        this.api.get('user/sign-out').subscribe((rsp: any) => {
        }, err => console.log(err));
        localStorage.removeItem('CURRENT_TOKEN');
        this.rou.navigate(['/']);
    }

    /**
     * Change password
     * @param info
     */
    public changePassword(info: any) {
        //info.oldpassword = this.rsa.encrypt(info.oldpassword); // encrypt password
        //info.newpassword = this.rsa.encrypt(info.newpassword); // encrypt password
        return this.api.post('portal-user/change-password', info);
    }

    /**
     * Reset password
     * @param info
     */
    public resetPassword(info: any) {
        //info.newpassword = this.rsa.encrypt(info.newpassword); // encrypt password
        return this.api.post('user/reset-password', info);
    }

    /**
     * Renew password
     * @param info
     */
    public verifyMail(info: any) {
        //info.newpassword = this.rsa.encrypt(info.newpassword); // encrypt password
        return this.api.post('portal-user/verify-mail', info);
    }

    /**
     * Forget password
     * @param info
     */
    public forgotPassword(info: any) {
        //info.newpassword = this.rsa.encrypt(info.newpassword); // encrypt password
        return this.api.post('user/forgot-password', info);
    }

    /**
     * Get configure
     */
    public getConfig() {
        return this.api.get('common/config');
    }

    /**
     * Get active code
     * @param type Mail or SMS
     */
    public getActiveCode(type: string) {
        let x = { keyword: type };
        return this.api.post('user/active-code', x);
    }

    /**
     * Save authentication
     * @param token
     * @param redirect
     */
    public saveAuth(token: string, redirect: boolean = true) {
        let t = this.api.saveToken(token);
        //this.timerLogout = Observable.interval(this.api.milliseconds);

        /*this.subscriptionLogout = this.timerLogout.subscribe(x => {
            let now = new Date();
            if (now > this.api.nextRun && this.api.allowLogout) {
                this.signOut();
            }
        });*/

        if (redirect) {
            //this.rou.navigate(['/dashboard']);
            //this.checkRedirect(t.user.accessrights);
        }
    }

    /**
     * Check access rights
     * @param right
     */
    public checkAccessRights(right: String): boolean {
        let res: any = JSON.parse(localStorage.getItem('CURRENT_TOKEN'));
        if (res == null) {
            return false;
        }

        if (right === '/dashboard') {
            right = 'Dashboard';
        }

        let ok = res.accessRights.find(x => x === right);
        return ok != undefined && ok != '';
    }

    /**
     * Check redirection
     */
    public checkRedirection() {
        let user = JSON.parse(localStorage.getItem('CURRENT_TOKEN'));
        if (user != null) {
            this.checkRedirect(user.accessRights);
        }
    }

    /**
     * Check redirect
     * @param ar Access rights
     */
    private checkRedirect(ar: any) {
        let dashboard = ar.find(myObj => myObj == 'Dashboard');
        let report = ar.find(myObj => myObj == 'Report');

        if (dashboard != undefined) {
            this.rou.navigate(['/dashboard']);
        }
        else {
            this.rou.navigate(['/error-page']);
        }
    }
}