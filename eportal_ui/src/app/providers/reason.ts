import { Injectable } from '@angular/core';
import { ApiProvider } from './api';

@Injectable()
export class ReasonProvider {

    constructor(private api: ApiProvider) { }

    /**
     * Search by
     * @param info
     */
    public search(info: any) {
        return this.api.post('reason/search', info);
    }

    /**
     * Get by sfId
     * @param info
     */
    public getById(info: any) {
        return this.api.post('reason/read', info);
    }
}