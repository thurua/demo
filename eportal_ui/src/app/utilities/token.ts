import { Injectable } from '@angular/core';
import { Utils } from 'app/utilities/utils';
import { JwtHelperService } from '@auth0/angular-jwt';

/**
 * @author ToanNguyen 2018-Oct-18 (verified)
 */
@Injectable()
export class Token {
    public static tokenInfo: any;
    public static runSignOut: Date;
    public static runRefresh: Date;
    public static lastAccess: Date;
    public static intSignOut = 0;
    public static intRefresh = 0;
    public static signOut = true;

    /**
     * Can refresh
     */
    public static canRefresh(): boolean {
        Utils.log("**********canRefresh()**********");

        let t1 = this.runRefresh <= this.lastAccess
        let t2 = this.lastAccess <= this.runSignOut;
        let res = t1 && t2;

        Utils.logDate("Ref", this.runRefresh);
        Utils.logDate("Las", this.lastAccess);
        Utils.logDate("Out", this.runSignOut);
        Utils.log("Ref " + this.runRefresh.getTime());
        Utils.log("Las " + this.lastAccess.getTime());
        Utils.log("Out " + this.runSignOut.getTime());
        Utils.log("Res " + res);

        Utils.log("********************************");

        return res;
    }

    /**
     * Update information
     */
    public static updateInfo(): void {
        let t = this.getInfo();
        if (t === null) { return; }

        Utils.log("*******Token.updateInfo()*******");
        this.tokenInfo = t;

        /* Timeline
        -Issued at---lastAccess-----runRefresh-----runSignOut------Expiration-->
            |            |<-intRefresh->|<---refresh--->|<---signOut--->|
            |            |<---------intSignOut--------->|               |
            |                           |<-----------buffer------------>|
        */

        // Time before Expiration to call sign out (second)
        let signOut = 15; // [10-30]
        if (signOut < 10) {
            signOut = 10;
            Utils.log("Time before to call sign out min = 10 seconds");
        }
        if (signOut > 30) {
            signOut = 30;
            Utils.log("Time before to call sign out max = 30 seconds");
        }
        Utils.log(signOut + " time before to call sign out (second)");
        //-------------------------------------------------------------

        // Time before _runSignOut to call refresh (second)
        let refresh = 15;[10 - 30]
        if (refresh < 10) {
            refresh = 10;
            Utils.log("Time before to call refresh min = 10 seconds");
        }
        if (refresh > 30) {
            refresh = 30;
            Utils.log("Time before to call refresh max = 30 seconds");
        }
        Utils.log(refresh + " time before to call refresh (second)");
        //-------------------------------------------------------------

        // Buffer (second)
        let buffer = signOut + refresh; // [20-60]
        Utils.log(buffer + " buffer (second)");
        //-------------------------------------------------------------

        // Token time (second)
        let sec = t.exp - t.iat;
        if (sec < 30) {
            sec = 30;
            Utils.log("Token time min = 30 seconds");
        }
        Utils.log(sec + " token time (second) | " + sec / 60 + " (minute)");
        //-------------------------------------------------------------

        // Issued at
        let iat = new Date(t.iat * 1000);
        Utils.logDate("Iat", iat);
        //-------------------------------------------------------------

        // Last access
        this.lastAccess = Utils.now();
        Utils.logDate("Las", this.lastAccess);
        //-------------------------------------------------------------

        // Run refresh
        let x = t.exp - buffer;
        this.runRefresh = new Date(x * 1000);
        Utils.logDate("Ref", this.runRefresh);
        //-------------------------------------------------------------

        // Run sign out
        x = t.exp - signOut;
        this.runSignOut = new Date(x * 1000);
        Utils.logDate("Out", this.runSignOut);
        //-------------------------------------------------------------

        // Expiration
        let exp = new Date(t.exp * 1000);
        Utils.logDate("Exp", exp);
        //-------------------------------------------------------------

        // Interval refresh
        x = this.runRefresh.getTime() - this.lastAccess.getTime();
        if (x <= 0) {
            x = signOut * 1000; // convert to millisecond
        }
        this.intRefresh = x;
        x = x / 1000; // convert to second
        Utils.log(x + " interval refresh (second) | " + x / 60 + " (minute)");
        //-------------------------------------------------------------

        // Interval sign out
        x = this.runSignOut.getTime() - this.lastAccess.getTime();
        if (x <= 0) {
            x = buffer * 1000; // convert to millisecond
        }
        this.intSignOut = x
        x = x / 1000; // convert to second
        Utils.log(x + " interval sign out (second) | " + x / 60 + " (minute)");
        //-------------------------------------------------------------

        // Valid interval refresh
        if (this.intRefresh === this.intSignOut) {
            x = refresh * 1000;// convert to millisecond
            if (this.intRefresh <= x) {
                this.intRefresh = this.intSignOut - x / 2;
                x = this.intRefresh / 1000; // convert to second
                Utils.log(x + " update interval refresh (second) | " + x / 60 + " (minute)");
            }
        }
        //-------------------------------------------------------------

        Utils.log("********************************");
    }

    /**
     * Get token in local storage
     */
    public static getToken(): string {
        let res = localStorage.getItem("Z-JWT");
        return res;
    }

    /**
     * Set token to local storage
     * @param token
     */
    public static setToken(token: string): void {
        let t = this.isExpired(token);
        if (t) { return; }

        this.removeToken();
        localStorage.setItem('Z-JWT', token);
        this.updateInfo();
    }

    /**
     * Remove token in local storage
     */
    public static removeToken(): void {
        localStorage.removeItem('Z-JWT');
    }

    /**
     * Decode token
     * @param token
     */
    public static decodeToken(token: string): any {
        let res: any = {};

        try {
            let t = new JwtHelperService();
            res = t.decodeToken(token);
        }
        catch (ex) {
            Utils.log(ex.message);
        }

        return res;
    }

    /**
     * Check token is expired
     * @param token
     */
    public static isExpired(token: string): boolean {
        let res = true;

        try {
            let t = new JwtHelperService();
            res = t.isTokenExpired(token);
        }
        catch (ex) {
            Utils.log(ex.message);
        }

        return res;
    }

    /**
     * Get user information
     */
    public static getUser(): any {
        let t = this.getInfo();
        let res = t.user;
        return res;
    }

    /**
     * Get token information
     */
    private static getInfo(): any {
        let t = this.getToken();
        let res = this.decodeToken(t);
        return res;
    }
}