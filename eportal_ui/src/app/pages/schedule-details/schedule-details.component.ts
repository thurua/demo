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

    public sfid: string = "";
    public entity: any = {};
    constructor(
        private act: ActivatedRoute,
        private pro: ScheduleProvider) { }

    ngOnInit() {


        this.act.params.subscribe((params: Params) => {
            this.sfid = params["_id"];
            this.getDetail(this.sfid );
        });
    }
    public getDetail(sfid) {
        this.pro.getById(sfid).subscribe((rsp: any) => {
            if (rsp.status === HTTP.STATUS_SUCCESS) {
                this.entity = rsp.result;
            }
        }, (err) => {
            console.log(err);
        });
    }
    public UpdateSchedule() {
        console.log(this.entity);

        let x = {
            currencyIsoCode:this.entity.currencyIsoCode,
            scheduleNo :this.entity.scheduleNo,
            factorCode :this.entity.factorCode,
            //recordTypeId:this.entity.recordTypeId,
            exchangeRate:this.entity.exchangeRate,
            id:this.entity.id
        }
        this.pro.update(x).subscribe((rsp: any) => {
            if (rsp.status === HTTP.STATUS_SUCCESS) {
                
            }
        }, (err) => {
            console.log(err);
        });
    }

}
