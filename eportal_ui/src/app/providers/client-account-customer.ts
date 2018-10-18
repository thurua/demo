import { Injectable } from '@angular/core';
import { ApiProvider } from './api';

@Injectable()
export class ClientAccountCustomerProvider {

    constructor(private api: ApiProvider) { }
}