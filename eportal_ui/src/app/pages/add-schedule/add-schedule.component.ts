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
    public vm: any = { id: "", scheduleType: "", scheduleNo: "", account: "", amendSchedule: false, clientAccount: "" };
    public msg = "";
    public message = "";
    public title = "";
    public success = false;
    public fileName = "";
    public validate: boolean = true;

    @ViewChild("infoModal") public infoModal: ModalDirective;

    constructor(
        private proCommon: CommonProvider,
        private proSchedule: ScheduleProvider,
        private pro: FileProvider) { }

    ngOnInit() {
        this.getClient();
        this.getClientAccount();
        this.hideShow("");
    }
    private getClient() {
        let x = {
            "filter": {
                "name": ""
            },
            "page": 1,
            "size": 10,
            "paging": false,
            "sort": [
                {
                    "field": "name",
                    "direction": ""
                }
            ]
        };

        this.proCommon.searchAccount(x).subscribe((rsp: any) => {
            console.log(rsp);
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
            "filter": {
                "client": this.vm.account
            },
            "page": 1,
            "size": 10,
            "paging": false,
            "sort": [
                {
                    "field": "clientAccount",
                    "direction": ""
                }
            ]
        };

        this.proSchedule.searchCA(x).subscribe((rsp: any) => {
            if (rsp.status === HTTP.STATUS_SUCCESS) {
                let item = {
                    id: null,
                    clientAccount: "-- Please select --"
                };

                if (rsp.result != null) {
                    rsp.result.data.unshift(item);
                    this.listClientAccount = rsp.result.data;
                }
            }
        }, (err) => {
            console.log(err);
        });
    }
    public changeAccount() {
        this.getClientAccount();
    }

    public fileChanged(e) {
        this.file = e.target.files[0];
    }

    public hideShow(a: any) {
        this.validate = true;

        if (a == "Invoice") {
            this.message = "Pursuant to the Factoring Agreement which we have made with you, we send you herewith the INVOICES and we hereby assign to you each of the debts to which those invoices relate. It is hereby guaranteed that in relation to the said invoices the warranties and undertakings contained in Clause (29) of the factoring Agreement have been complied with and in particular the goods and/or services have been delivered and/or performed prior to the date hereof.";
            this.fileName = "Factoring-INV.xlsx";
            this.validate = false;
        }
        else if (a == "Cash Disbursement") {
            this.message = "Pursuant to the Factoring Agreement which we have made with you, we send you herewith the INVOICES and we hereby assign to you each of the debts to which those invoices relate. It is hereby guaranteed that in relation to the said invoices the warranties and undertakings contained in Clause (29) of the factoring Agreement have been complied with and in particular the goods and/or services have been delivered and/or performed prior to the date hereof.";
            this.fileName = "Loan-INV.xlsx";
            this.validate = false;
        }
        else if (a == "Credit note") {
            this.message = "In accordance with the Factoring Agreement we have made with you, we send you herewith a COPY OF THE ORIGINAL CREDIT NOTES relating to the customers listed above.";
            this.fileName = "Factoring-CN.xlsx";
            this.validate = false;
        }
        else if (a == "Required") {
            this.validate = false;
        };
    }

    public uploadDocument() {
        var acceptanceDate = new Date(this.vm.date);

        let o =
        {
            "clientId": this.vm.account,
            "scheduleNo": this.vm.scheduleNo,
            "acceptanceDate": "2018-10-01T14:48:00.000Z",
            "amendSchedule": this.vm.amendSchedule,
            "clientAccountId": this.vm.clientAccount,
            "scheduleType": this.vm.scheduleType
        };
        let s = JSON.stringify(o);
        this.pro.upload(this.file, s).subscribe((rsp: any) => {
            if (rsp.body != undefined) {
                let o = JSON.parse(rsp.body);

                if (o.status === HTTP.STATUS_SUCCESS) {
                    this.title = "Information";
                    this.msg = "New Schedule of Offer is created, successfully!";
                }
                else {
                    this.title = "Validation Errors";
                    this.msg = "Follingwing validation errors are found in the uploaded excel. Please rectify and reupload the file.";
                }
                this.infoModal.show();
            }
        }, err => console.log(err));
    }
}