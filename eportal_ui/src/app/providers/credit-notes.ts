import { Injectable } from '@angular/core';
import { ApiProvider } from './api';

@Injectable()
export class CreditNoteProvider {

    constructor(private api: ApiProvider) { }

    /**
     * Search by
     * @param info
     */
    public search(info: any) {
        return this.api.post('credit-note/search', info);
    }

    /**
     * Create
     * @param info
     */
    public create(info: any) {
        return this.api.post('schedule-of-offer/create', info);
    }

    /**
     * Update
     * @param info
     */
    public update(info: any) {
        return this.api.post('schedule-of-offer/update-schedule-details', info);
    }


    /**
     * Search Client Account
     * @param info
     */
    public searchCA(info: any) {
        return this.api.post('common/search-client-account', info);
    }
/**
     * Search Customer
     * @param info
     */
    public searchCustomer(info: any) {
        return this.api.post('common/search-customer', info);
    }

    /**
     * Get by Id
     * @param info
     */
    public getById(info: any) {
        return this.api.post('credit-note/read', info);
    }
}