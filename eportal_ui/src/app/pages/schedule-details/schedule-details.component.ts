import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { ScheduleProvider } from '../../providers/schedule';
import { ActivatedRoute, Params } from '@angular/router';
import { HTTP } from '../../utilities/const';

@Component({
    selector: 'app-schedule-details',
    templateUrl: './schedule-details.component.html',
    styleUrls: ['./schedule-details.component.scss'],
    encapsulation: ViewEncapsulation.None
})
export class ScheduleDetailsComponent implements OnInit {

    public id: string = "";
    public data = {
        "scheduleNo": "",
        "clientName": "",
        "clientAccount": "",
        "scheduleStatus": "",
        "scheduleDate": "",
        "currency": "",
        "totalNoInvoice": "",
        "totalAmountInvoice": "",
        "documentType": "",
        "exchangeRate": "",
        "factorCode": ""
    };
    constructor(
        private act: ActivatedRoute,
        private pro: ScheduleProvider) { }

    ngOnInit() {


        this.act.params.subscribe((params: Params) => {
            this.id = params["_id"];
            console.log(this.id);
            this.id = "a1Pp0000002QWmoEAG";
            this.getDetail();
        });
    }
    public getDetail() {
        let x = this.id
        this.pro.getById(x).subscribe((rsp: any) => {
            console.log(rsp);
            if (rsp.status === HTTP.STATUS_SUCCESS) {
                this.data = rsp.result;
            }
        }, (err) => {
            console.log(err);
        });

    }

}
