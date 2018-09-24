import { Injectable } from '@angular/core';

@Injectable()
export class Utils {
    /**
     * Calculate next working day
     * @param input Date input
     * @param days Number of days
     * @param isCalendar Calendar mode (include Sat and Sun)
     */
    public nextWorkingDay(input: Date, days: number, isCalendar: boolean) {
        let d: Date = new Date(input);

        if (!isCalendar) {
            let week = Math.floor(days / 5);
            let addDays = 0;

            switch (d.getDay()) {
                case 0: // sunday
                    if (week < days / 5) {
                        addDays = days + 2 * week;
                    } else {
                        addDays = days + 2 * (week - 1);
                    }
                    break;

                case 6: // saturday
                    if (week < days / 5) {
                        addDays = days + 1 + 2 * week;
                    } else {
                        addDays = days + 1 + 2 * (week - 1);
                    }
                    break;

                default: // monday -> friday
                    let dayInWeek = [5, 4, 3, 2, 1];
                    let afk = days % 5;

                    if (afk == 0) {
                        addDays = days + 2 * week;
                    } else {
                        if (dayInWeek[d.getDay() - 1] <= afk)
                            addDays = days + 2 * (week + 1);
                        else
                            addDays = days + 2 * week;
                    }
                    break;
            }

            let ok = d.getDate() + addDays;
            return new Date(d.setDate(ok));
        } else {
            let tmp = d.getDate() + days;
            let res = new Date(d.setDate(tmp));
            let dow = res.getDay();

            if (dow === 0) { // sunday
                days -= 2;
            }
            if (dow === 6) { // saturday
                days--;
            }

            d = new Date(input);
            res = new Date(d.setDate(input.getDate() + days));
            return res;
        }
    }
}