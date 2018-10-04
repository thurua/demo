import { Injectable } from '@angular/core';
import { DatePipe } from '@angular/common';

@Injectable()
export class Utils {
    public formatDate(d: Date, format: string) {
        let res = "";

        if (d != null) {
            let datePipe = new DatePipe('en-us');
            res = datePipe.transform(d, format);
        }

        return res;
    }

    // Add months
    public addMonths(date: Date, month: any) {
        let tmp = date.getMonth() + month;
        date = new Date(date.setMonth(tmp));

        return date;
    }
}