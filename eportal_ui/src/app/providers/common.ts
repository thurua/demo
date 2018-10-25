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
    public searchCA(info: any) {
        return this.api.post('common/search-client-account', info);
    }

    /**
     * Search customer
     */
    public searchCU(info: any) {
        return this.api.post('common/search-customer', info);
    }

    /**
     * Search supplier
     */
    public searchSU(info: any) {
        return this.api.post('common/search-supplier', info);
    }

    /**
     * Search Customer
     * @param info
     */
    public searchCustomer(info: any) {
        let x = {};
        return this.api.post('common/search-customer', info);
    }
}