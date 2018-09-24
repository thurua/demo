import 'rxjs/add/operator/toPromise';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { ApiProvider } from '../api/api';

@Injectable()
export class SalesforceProvider {
    constructor(private api: ApiProvider, private rou: Router) { }

    /**
    * Get API URL
    */
    public getURL() {
        return this.api.imgUrl + "/";
    }

    /**
    * Handle error
    * @param error
    */
    public handleError(error: any) {
        return "Error message";
    }

    /**
     * Get all package
     * @param isPromo
     */
    public getPackage(isPromo: Boolean = false) {
        let tmp = 'package/';

        if (isPromo) {
            tmp += '?isPromo=true';
        }

        return this.api.get(tmp);
    }

    /**
     * Search package
     * @param info
     */
    public searchPackage(info: any) {
        return this.api.post('package/', info);
    }

    /**
     * Check promo code
     * @param promoCode
     */
    public checkPromoCode(promoCode: String) {
        return this.api.get('package/valid?promoCode=' + promoCode);
    }

    /**
     * Save lead
     * @param info
     */
    public saveLead(info: any) {
        let d = new Date();
        let offset = d.getTimezoneOffset() * -60;
        info.offset = offset;
        return this.api.post('lead/save', info);
    }

    /**
     * Check company name + postal code is unique in salesforce site
     * @param info
     */
    public validLead(info: any) {
        return this.api.post('lead/valid', info);
    }

    /**
     * Search post code, block number and street name
     * @param info
     */
    public sgLocate(info: any) {
        return this.api.post('lead/sgLocate', info);
    }

    /**
     * Download PDF file
     * @param file
     */
    public download(file: String) {
        let info = {
            "keyword": file
        };

        return this.api.download('lead/download', info);
    }

    /**
     * Get environment variable for business form
     */
    public getFormData() {
        return this.api.get('lead/formData');
    }

    /**
     * Get environment variable for residential form
     * @param isPromo
     */
    public getPackageData(isPromo: Boolean = false) {
        let tmp = 'lead/packageData';

        if (isPromo) {
            tmp += '?isPromo=true';
        }

        return this.api.get(tmp);
    }
}