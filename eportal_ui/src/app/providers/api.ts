import { environment } from '../../environments/environment';
import { Injectable } from '@angular/core';
import { HTTP, Utils, Token } from 'app/utilities';
import { HttpClient, HttpParams, HttpHeaders, HttpRequest, HttpEvent } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable } from 'rxjs/Observable';

/**
 * API is a generic REST Api handler. Set your API url first
 */
@Injectable()
export class ApiProvider {
    public apiUrl = "";
    public imgUrl = "";

    constructor(private http: HttpClient, private rou: Router) {
        if (environment.production) {
            let tmp = !environment.apiUrl.startsWith(location.origin) ? location.origin : "";
            this.apiUrl = tmp + environment.apiUrl;

            tmp = !environment.imgUrl.startsWith(location.origin) ? location.origin : "";
            this.imgUrl = tmp + environment.imgUrl;
        }
        else {
            this.apiUrl = environment.apiUrl;
            this.imgUrl = environment.imgUrl;
        }

        Token.updateInfo();
    }

    public get(endpoint: string, isAuth: boolean = true, params?: any, reqOpts?: any) {
        let token = "";

        if (isAuth) {
            token = this.getToken();
            if (token === "") {
                this.rou.navigate(["/"]);
                return new Observable<ArrayBuffer>();
            }
        }

        if (!reqOpts) {
            reqOpts = {
                headers: new HttpHeaders().set('Authorization', token),
                params: new HttpParams()
            };
        }

        // Support easy query params for GET requests
        if (params) {
            reqOpts.params = new HttpParams();
            for (let k in params) {
                reqOpts.params.set(k, params[k]);
            }
        }

        return this.http.get(this.apiUrl + endpoint, reqOpts);
    }

    public post(endpoint: string, body: any, isAuth: boolean = true, reqOpts?: any) {
        let token = "";

        if (isAuth) {
            token = this.getToken();
            if (token === "") {
                this.rou.navigate(["/"]);
                return new Observable<ArrayBuffer>();
            }
        }

        if (!reqOpts) {
            let h = new HttpHeaders().set('Authorization', token)
            h = h.append('Content-Type', 'application/json');
            reqOpts = { headers: h };
        }

        return this.http.post(this.apiUrl + endpoint, body, reqOpts);
    }

    public upload(endpoint: string, data: FormData, isAuth: boolean = true): Observable<HttpEvent<{}>> {
        let token = "";

        if (isAuth) {
            token = this.getToken();
            if (token === "") {
                this.rou.navigate(["/"]);
                return new Observable<HttpEvent<{}>>();
            }
        }

        let h = new HttpHeaders().set('Authorization', token)

        let req = new HttpRequest('POST',
            this.apiUrl + endpoint,
            data,
            {
                headers: h,
                reportProgress: true,
                responseType: 'text'
            });

        return this.http.request(req);
    }

    public download(endpoint: string, body: any, isAuth: boolean = true, reqOpts?: any): Observable<any> {
        let token = "";

        if (isAuth) {
            token = this.getToken();
            if (token === "") {
                this.rou.navigate(["/"]);
                return new Observable<any>();
            }
        }

        if (!reqOpts) {
            reqOpts = {
                headers: new HttpHeaders().set('Authorization', token),
                responseType: 'blob'
            };
        }

        return this.http.post(this.apiUrl + endpoint, body, reqOpts);
    }

    /**
     * Get token
     */
    private getToken(): string {
        let res = "";

        Utils.log("***********getToken()***********");

        let t = Token.getToken();
        let ok = Token.isExpired(t);
        if (!ok) {
            res = "Bearer " + t;
            Token.lastAccess = Utils.now();
            Utils.logDate("Las", Token.lastAccess);
        }

        Utils.log("********************************");

        return res;
    }
}