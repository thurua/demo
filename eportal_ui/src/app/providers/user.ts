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
     * Read
     */
    public read() {
        let x = {};
        return this.api.post('portal-user/read', x);
    }

    /**
     * Update profile
     * @param info
     */
    public updateProfile(info: any) {
        return this.api.post('portal-user/update-profile', info);
    }

    /**
     * Update password
     * @param info
     */
    public updatePassword(info: any) {
        //info.oldpassword = this.rsa.encrypt(info.oldpassword); // encrypt password
        //info.newpassword = this.rsa.encrypt(info.newpassword); // encrypt password
        return this.api.post('portal-user/update-password', info);
    }

    /**
     * Reset password
     * @param info
     */
    public resetPassword(info: any) {
        return this.api.post('portal-user/reset-password', info);
    }

    /**
     * Update token
     * @param info
     */
    public updateToken(info: any) {
        return this.api.post('portal-user/update-token', info);
    }

    /**
     * Search by
     * @param info
     */
    public search(info: any) {
        return this.api.post('portal-user/search', info);
    }

    /**
     * Sign in
     * @param info
     */
    public signIn(info: any) {
        info.password = this.rsa.encrypt(info.password); // encrypt password
        return this.api.post('portal-user/sign-in', info);
    }

    /**
     * Sign out
     */
    public signOut() {
        this.api.get('portal-user/sign-out').subscribe((rsp: any) => {
        }, err => console.log(err));

        localStorage.removeItem('CURRENT_TOKEN');
        this.rou.navigate(['/']);
    }

    /**
     * Get configuration
     */
    public getConfig() {
        return this.api.get('portal-user/get-config');
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

    /**
     * Check Expired
     * @param info
     */
    public checkExpired(info: any) {
        return this.api.post('portal-user/checkExpired', info);
    }
}