import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { DatePipe } from '@angular/common';

/**
 * @author ToanNguyen 2018-Oct-18 (verified)
 */
@Injectable()
export class Utils {
    /**
     * Write log
     * @param o Date
     */
    public static log(o: any): void {
        if (environment.showLog) {
            let t = this.format(new Date(), "dd-MMM-yyy HH:mm:ss")
            console.log(t + " | " + o);
        }
    }

    /**
     * Write log
     * @param s Name
     * @param d Value
     */
    public static logDate(s: string, d: Date): void {
        let t = this.format(d, "dd-MMM-yyy HH:mm:ss")
        this.log(s + " " + t);
    }

    /**
     * Add months
     * @param d Date
     * @param n Month
     */
    public static addMonths(d: Date, n: number): Date {
        let t = d.getMonth() + n;
        let res = new Date(d.setMonth(t));
        return res;
    }

    /**
     * Now
     */
    public static now(): Date {
        let t = new Date();
        let ten = t.setMilliseconds(0);
        let res = new Date(ten);
        return res;
    }

    /**
     * Convert to string
     * @param d Date and time
     * @param f String format
     */
    public static format(d: Date, f: string): string {
        let res = "";

        if (d != null) {
            let datePipe = new DatePipe('en-us');
            res = datePipe.transform(d, f);
        }

        return res;
    }
}