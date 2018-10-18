import { Injectable } from '@angular/core';
import { ApiProvider } from './api';

@Injectable()
export class ScheduleProvider {

    constructor(private api: ApiProvider) { }

    public getApiUrl() {
        return this.api.apiUrl;
    }

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
     * Update
     * @param info
     */
    public update(info: any) {
        return this.api.post('schedule-of-offer/update-schedule-details', info);
    }

    /**
     * Get by Idt
     * @param info
     */
    public getById(info: any) {
        return this.api.post('schedule-of-offer/read', info);
    }
}