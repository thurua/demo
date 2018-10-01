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
}