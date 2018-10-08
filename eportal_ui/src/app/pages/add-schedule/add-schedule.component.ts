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
    public clientId: string = "";
    public clientName: string = "";
    public lstCA: any[] = [];
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
    public isCheck: boolean = false;
    public clientAccountId = "";

    @ViewChild("infoModal") public infoModal: ModalDirective;

    constructor(
        private proSchedule: ScheduleProvider,
        private pro: FileProvider) { }

    ngOnInit() {
        let user = JSON.parse(localStorage.getItem("CURRENT_TOKEN"));
    this.clientId = user.clientId;
    this.clientName = user.clientName;
    this.searchCA();
        this.hideShow("");
    }
    public searchCA() {
        let x = {
          filter: {
            client: this.clientId
          },
          page: 1,
          size: 20
        }
    
        this.proSchedule.searchCA(x).subscribe((rsp: any) => {
          let item = {
            sfid: "",
            clientAccount: "-- Please select --"
          }
    
          if (rsp.status === HTTP.STATUS_SUCCESS) {
    
            rsp.result.data.unshift(item);
            this.lstCA = rsp.result.data;
          }
          else {
            this.lstCA.unshift(item);
          }
        }, (err) => {
          console.log(err);
        });
      }

    public fileChanged(e) {
        this.file = e.target.files[0];
    }

    public hideShow(a: any) {
        this.validate = true;

        if (a == "Invoice") {
            this.message = "Pursuant to the Factoring Agreement which we have made with you, we send you herewith the <b><u>INVOICES</u></b> and we hereby assign to you each of the debts to which those invoices relate.<br/>It is hereby guaranteed that in relation to the said invoices the warranties and undertakings contained in Clause (29) of the factoring Agreement have been complied with and in particular the goods and/or services have been delivered and/or performed prior to the date hereof.";
            this.fileName = "Factoring-INV.xlsx";
            this.validate = false;
            this.isCheck = true;
        }
        else if (a == "Cash Disbursement") {
            this.message = "Pursuant to the Factoring Agreement which we have made with you, we send you herewith the <b><u>INVOICES</b></u> and we hereby assign to you each of the debts to which those invoices relate.<br/>It is hereby guaranteed that in relation to the said invoices the warranties and undertakings contained in Clause (29) of the factoring Agreement have been complied with and in particular the goods and/or services have been delivered and/or performed prior to the date hereof.";
            this.fileName = "Loan-INV.xlsx";
            this.validate = false;
            this.isCheck = true;
        }
        else if (a == "Credit note") {
            this.message = "In accordance with the Factoring Agreement we have made with you, we send you herewith a <b><u>COPY OF THE ORIGINAL CREDIT NOTES</b></u> relating to the customers listed above.";
            this.fileName = "Factoring-CN.xlsx";
            this.validate = false;
            this.isCheck = true;
        }
        else if (a == "Required") {
            this.validate = false;
        };
    }

    public uploadDocument() {
        var acceptanceDate = new Date(this.vm.date);

        let o =
        {
            "clientId": this.clientId,
            "scheduleNo": this.vm.scheduleNo,
            "acceptanceDate": "2018-10-01T14:48:00.000Z",
            "amendSchedule": this.vm.amendSchedule,
            "clientAccountId": this.vm.clientAccountId,
            "scheduleType": this.vm.scheduleType
        };
        console.log(o);
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