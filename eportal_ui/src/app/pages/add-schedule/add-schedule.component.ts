import { Component, OnInit, ViewEncapsulation, ViewChild } from '@angular/core';
import { CommonProvider, FileProvider, ScheduleProvider } from '../../providers/provider';
import { HTTP } from '../../utilities/utility';
import { FileUploader } from 'ng2-file-upload';
import { ModalDirective } from 'ngx-bootstrap';

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
    public loader: boolean = false;
    public vm: any = { id: "", scheduleType: "Invoice", scheduleNo: "", account: "", amendSchedule: false, date: null, clientAccount: "" };
    public msg = "";
    public title = "";
    public success = false;

    @ViewChild("informationModal") public informationModal: ModalDirective;

    constructor(
        private proCommon: CommonProvider,
        private proSchedule: ScheduleProvider,
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
        let x = {
            filter: {
                client: this.vm.account
            },
            page: 1,
            size: 20
        }

        this.proSchedule.searchCA(x).subscribe((rsp: any) => {
            if (rsp.status === HTTP.STATUS_SUCCESS) {
                let item = {
                    id: null,
                    clientAccount: "-- Please select --"
                }
                rsp.result.data.unshift(item);

                this.listClientAccount = rsp.result.data;
            }
        }, (err) => {
            console.log(err);
        });
    }
    public changeAccount()
    {
        this.getClientAccount();
    }

    public fileChanged(e) {
        this.file = e.target.files[0];
    }

    public uploadDocument() {
        var acceptanceDate = new Date(this.vm.date);
        let o =
        {
            "clientId": this.vm.account,
            "scheduleNo": this.vm.scheduleNo,
            "acceptanceDate": acceptanceDate.toISOString(),
            "amendSchedule": this.vm.amendSchedule,
            "clientAccountId": this.vm.clientAccount,
            "scheduleType": this.vm.scheduleType
        };
        let s = JSON.stringify(o);

        this.pro.upload(this.file, s).subscribe((rsp: any) => {
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

    public create() {
        this.loader = true;

        this.proSchedule.create(this.vm).subscribe((rsp: any) => {
            if (rsp.status === HTTP.STATUS_SUCCESS) {
                this.title = "Confirmation";
                this.msg = "A schedule of offer has been created successfully, but thể are isues on Invoice/Credit Note/Cash Disbursement details.";
                this.success = true;
            } else {
                this.title = "Validation Erors";
                this.msg = "Follingwing validation errors are found in the uploaded excel. Please rectify and reupload the file.";
                this.success = false;
            }
            this.informationModal.show();

            this.loader = false;
        }, err => console.log(err));
    }
}