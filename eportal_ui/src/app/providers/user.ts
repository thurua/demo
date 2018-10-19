import { Injectable } from '@angular/core';
import { HTTP, Utils, Token, Rsa } from 'app/utilities';
import { ApiProvider } from 'app/providers';
import { Router } from '@angular/router';
import { interval } from 'rxjs';

@Injectable()
export class UserProvider {
    private timerRefresh;
    private timerSignOut;

    constructor(private api: ApiProvider,
        private rou: Router,
        private rsa: Rsa) { }

    /**
     * Read
     */
    public read() {
        return this.api.post("portal-user/read", {});
    }

    /**
     * Update profile
     * @param info
     */
    public updateProfile(info: any) {
        return this.api.post("portal-user/update-profile", info);
    }

    /**
     * Update password
     * @param info
     */
    public updatePassword(info: any) {
        info.oldpassword = this.rsa.encrypt(info.oldpassword); // encrypt password
        info.newpassword = this.rsa.encrypt(info.newpassword); // encrypt password
        return this.api.post("portal-user/update-password", info);
    }

    /**
     * Reset password
     * @param info
     */
    public resetPassword(info: any) {
        return this.api.post("portal-user/reset-password", info, false);
    }

    /**
     * Update token
     * @param info
     */
    public updateToken(info: any) {
        return this.api.post("portal-user/update-token", info, false);
    }

    /**
     * Search by
     * @param info
     */
    public search(info: any) {
        return this.api.post("portal-user/search", info);
    }

    /**
     * Sign in
     * @param info
     */
    public signIn(info: any) {
        info.password = this.rsa.encrypt(info.password); // encrypt password
        return this.api.post("portal-user/sign-in", info, false, false);
    }

    /**
    * Refresh token
    */
    public refreshToken() {
        let t = this.api.post("portal-user/refresh-token", {}, true, false);
        t.subscribe((rsp: any) => {
            if (rsp.status === HTTP.STATUS_SUCCESS) {
                Token.signOut = true;
                Token.setToken(rsp.result);
            }
            else {
                Utils.log(rsp.message);
            }
        }, err => Utils.log(err));
    }

    /**
     * Sign out
     */
    public signOut() {
        let t = this.api.post("portal-user/sign-out", {}, true, false);
        t.subscribe(() => {
            Token.signOut = false;
            Token.removeToken();
            this.rou.navigate(["/"]);
        }, err => Utils.log(err));
    }

    /**
     * Get configuration
     */
    public getConfig() {
        return this.api.get("portal-user/get-config", false, false);
    }

    /**
     * Check expired
     * @param info
     */
    public checkExpired(info: any) {
        return this.api.post("portal-user/check-expired", info, false);
    }

    /**
     * Save authentication
     * @param token
     * @param redirect
     */
    public saveAuth(token: string, redirect: boolean = true) {
        if (this.timerSignOut) {
            clearInterval(this.timerSignOut);
        }

        Token.setToken(token);

        this.timerSignOut = setInterval(() => {
            Utils.log("Run this.timerSignOut");

            let t = Token.runSignOut.getTime() - Token.lastAccess.getTime();
            if (t < Token.intSignOut) {
                Utils.log("Call this.refreshToken()");
                this.refreshToken();
            }
            else {
                Utils.log("Call this.signOut()");

                if (this.timerSignOut) {
                    clearInterval(this.timerSignOut);
                }

                this.signOut();
            }
        }, Token.intSignOut);

        if (redirect) {
            this.rou.navigate(["/pages/dashboard"]);
        }
    }
}