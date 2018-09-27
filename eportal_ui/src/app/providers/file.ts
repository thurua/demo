import { Injectable } from '@angular/core';
import { ApiProvider } from './api';

@Injectable()
export class FileProvider {
    constructor(private api: ApiProvider) { }

    /**
     * Upload
     * @param info
     */
    public upload(info: File, data: any) {
        let f: FormData = new FormData();
        f.append('file', info);
        f.append('req', data);
        return this.api.upload('file/upload', f);
    }

    /**
     * Call
     * @param info
     */
    public call(info: File, data: any) {
        let f: FormData = new FormData();
        f.append('file', info);
        f.append('req', data);
        return this.api.upload('file/call', f);
    }
}