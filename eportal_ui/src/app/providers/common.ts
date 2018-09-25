import { Injectable } from '@angular/core';
import { ApiProvider } from './api';

@Injectable()
export class CommonProvider {
    constructor(private api: ApiProvider) { }

    /**
     * Search account
     */
    public searchAccount() {
        let x = {};
        return this.api.post('common/search-account', x);
    }

    /**
     * Search client account
     */
    public searchClientAccount() {
        let x = {};
        return this.api.post('common/search-client-account', x);
    }
}