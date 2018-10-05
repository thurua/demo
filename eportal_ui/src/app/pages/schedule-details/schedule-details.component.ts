import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { ScheduleProvider } from '../../providers/schedule';
import { ActivatedRoute, Params } from '@angular/router';
import { HTTP } from '../../utilities/const';
import { FileUploader } from 'ng2-file-upload';
import { CommonProvider } from '../../providers/common';
import { FileProvider } from '../../providers/file';

const URL = 'http://localhost:8080/api/upload';

@Component({
    selector: 'app-schedule-details',
    templateUrl: './schedule-details.component.html',
    styleUrls: ['./schedule-details.component.scss'],
    encapsulation: ViewEncapsulation.None
})


export class ScheduleDetailsComponent implements OnInit {

    public sfid: string = "";
    public entity: any = {};
    public file: any;
    public uploader: FileUploader = new FileUploader({ url: URL, itemAlias: 'photo' });
    constructor(
        private act: ActivatedRoute,
        private pro: ScheduleProvider,
        private proCommon: CommonProvider,
        private filepro: FileProvider) { }

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
    public fileChanged(e) {
        console.log(e);
        this.file = e.target.files;
        console.log(e.target.files);
    }
    
    public UpdateFile()
    {
        let o =
        {
            "scheduleOfOfferId": "001p000000UraJCAAZ"
        };
        let s = JSON.stringify(o);
        this.filepro.uploadmulti(this.file, s).subscribe((rsp: any) => {
            if (rsp.body != undefined) {
                let o = JSON.parse(rsp.body);
            }
        }, err => console.log(err));
    }


}
