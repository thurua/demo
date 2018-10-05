import { Injectable } from '@angular/core';
import { ApiProvider } from './api';

@Injectable()
export class ScheduleProvider {

    constructor(private api: ApiProvider) { }

    /**
     * Search by
     * @param info
     */
    public search(info: any) {
        return this.api.post('schedule-of-offer/search', info);
    }

    /**
     * Create
     * @param info
     */
    public create(info: any) {
        return this.api.post('schedule-of-offer/create', info);
    }

    /**
     * Search Client Account
     * @param info
     */
    public searchCA(info: any) {
        return this.api.post('common/search-client-account', info);
    }
    /**
     * Get by Idt
     * @param info
     */
    public getById(info: any) {
        return this.api.post('schedule-of-offer/read', info);
    }
}