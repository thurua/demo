import { Injectable } from '@angular/core';
import { ApiProvider } from './api';

@Injectable()
export class FundRequestProvider {

    constructor(private api: ApiProvider) { }

    public getApiUrl() {
        return this.api.apiUrl;
    }

    /**
     * Search by
     * @param info
     */
    public search(info: any) {
        return this.api.post('fund-request/search', info);
    }
}