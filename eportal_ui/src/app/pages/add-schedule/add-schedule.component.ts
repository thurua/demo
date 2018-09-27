import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { CommonProvider, FileProvider} from '../../providers/provider';
import { HTTP } from '../../utilities/utility';
import { FileUploader } from 'ng2-file-upload';

const URL = 'http://localhost:8080/api/upload';

@Component({
    selector: 'app-add-schedule',
    templateUrl: './add-schedule.component.html',
    styleUrls: ['./add-schedule.component.scss'],
    encapsulation: ViewEncapsulation.None
})
export class AddScheduleComponent implements OnInit {
    public listClient: any[] = [];
    public listClientAccount: any[] = [];
    public uploader: FileUploader = new FileUploader({ url: URL, itemAlias: 'photo' });
    public file: any;

    constructor(
        private proCommon: CommonProvider,
        private pro: FileProvider) { }

    ngOnInit() {
        this.getClient();
        this.getClientAccount();
    }
    private getClient() {
        this.proCommon.searchAccount().subscribe((rsp: any) => {
            if (rsp.status === HTTP.STATUS_SUCCESS) {
                this.listClient = rsp.result.data;
            }
            else {
                console.log(rsp.message);
            }
        }, err => console.log(err));
    }

    private getClientAccount() {
        this.proCommon.searchClientAccount().subscribe((rsp: any) => {
            if (rsp.status === HTTP.STATUS_SUCCESS) {
                this.listClientAccount = rsp.result.data;
            }
            else {
                console.log(rsp.message);
            }
        }, err => console.log(err));
    }

    public fileChanged(e) {
        this.file = e.target.files[0];
    }

    public uploadDocument() {
        let o = {
            clientName: "sdsd",
            scheduleAcceptanceDate: "2",
            amendSchedule: true,
            amendScheduleNo: "no1"
        };
        let s = JSON.stringify(o);

        this.pro.call(this.file, s).subscribe((rsp: any) => {
            if (rsp.body != undefined) {
                let o = JSON.parse(rsp.body);

                if (o.status === HTTP.STATUS_SUCCESS) {
                    alert("File is uploaded!");
                }
                else {
                    console.log(o.message);
                }
            }
        }, err => console.log(err));
    }
}
