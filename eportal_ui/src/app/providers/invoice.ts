import { Injectable } from '@angular/core';
import { ApiProvider } from './api';

@Injectable()
export class InvoiceProvider {

    constructor(private api: ApiProvider) { }

    /**
     * Search by
     * @param info
     */
    public search(info: any) {
        return this.api.post('invoice/search', info);
    }

    /**
     * Create
     * @param info
     */
    public create(info: any) {
        return this.api.post('invoice/create', info);
    }

    /**
     * Update
     * @param info
     */
    public update(info: any) {
        return this.api.post('invoice/update-schedule-details', info);
    }

    /**
     * Search Customer
     * @param info
     */
    public searchCustomer(info: any) {
        return this.api.post('credit-note/search-customer', info);
    }

    /**
     * Get by Idt
     * @param info
     */
    public getById(info: any) {
        return this.api.post('invoice/read', info);
    }
}