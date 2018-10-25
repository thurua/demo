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
     * Get by Id
     * @param info
     */
    public getById(info: any) {
        return this.api.post('credit-note/read', info);
    }
}