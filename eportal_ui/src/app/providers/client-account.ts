import { Injectable } from '@angular/core';
import { ApiProvider } from './api';

@Injectable()
export class ClientAccountProvider {

    constructor(private api: ApiProvider) { }

    /**
     * Search by
     * @param info
     */
    public search(info: any) {
        return this.api.post('client-account/search', info);
    }

    /**
     * Get details
     * @param info
     */
    public getDetails(info: any) {
        return this.api.post('client-account/get-details', info);
    }

    /**
     * Get Client Account Customer
     * @param info
     */
    public getClientAccountCustomer(info: any) {
        return this.api.post('client-account/client-account-customer', info);
    }
}