import { Injectable } from '@angular/core';
import { ApiProvider } from './api';

@Injectable()
export class CommonProvider {
    constructor(private api: ApiProvider) { }

    /**
     * Search account
     */
    public searchAccount(info: any) {
        return this.api.post('common/search-account', info);
    }

    /**
     * Search client account
     */
    public searchClientAccount() {
        let x = {};
        return this.api.post('common/search-client-account', x);
    }
}