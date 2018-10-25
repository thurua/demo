import { Component, OnInit, ViewEncapsulation, ViewChild } from '@angular/core';
import { Utils, Token, HTTP } from 'app/utilities';
import { ScheduleProvider, CommonProvider } from 'app/providers';
import { ModalDirective } from 'ngx-bootstrap';

@Component({
    selector: 'app-add-fund-request',
    templateUrl: './add-fund-request.component.html',
    styleUrls: ['./add-fund-request.component.scss'],
    encapsulation: ViewEncapsulation.None
})
export class AddFundRequestComponent implements OnInit {
    public clientId: string = "";
    public clientName: string = "";
    public lstCA: any[] = [];
    public lstType: any[] = [];
    public fundType = "";
    public clientAccountId = "";
    public data = [];
    public isCollapsed: boolean = false;

    public entity: any = {};
    public title = "";
    public msgInfo = "";

    @ViewChild('infoModal') public infoModal1: ModalDirective;
    @ViewChild('discardModal') public discardModal: ModalDirective;

    constructor(private pro: ScheduleProvider,
        private proCommon: CommonProvider) { }

    ngOnInit() {


        let user = Token.getUser();
        this.clientId = user.clientId;
        this.clientName = user.clientName;

        this.searchCA();

        let tmpType = {
            data: [{
                code: "",
                value: "-- Please Select --"
            }, {
                code: "Factoring",
                value: "Factoring"
            }, {
                code: "Loan",
                value: "Loan"
            }]
        }
        this.lstType = tmpType.data;
    }

    public hideAndReset() {
        this.resetClick();
        this.infoModal1.hide();
    }

    public discardAndReset() {
        this.resetClick();
        this.discardModal.hide();
    }

    public resetClick() {
        this.fundType = "";
        this.clientAccountId = "";
    }

    public searchCA() {
        document.getElementById('preloader').style.display = 'block';
        let x = {
            filter: {
                client: this.clientId,
                status: ""
            },
            page: 1,
            size: 20,
            sort: [
                {
                    direction: "ASC",
                    field: "clientAccount"
                }
            ]
        }

        this.proCommon.searchCA(x).subscribe((rsp: any) => {
            let item = {
                sfId: "",
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

        setTimeout(function () {
            document.getElementById('preloader').style.display = 'none';
        }, 500);
    }

    public proceed(valid: any) { }

    public Discard() {
        let x = {
            // scheduleNo: this.entity.scheduleNo,
            // portalStatus: "Discarded",
            // id: this.entity.id
        }
        this.pro.update(x).subscribe((rsp: any) => {
            if (rsp.status === HTTP.STATUS_SUCCESS) {
                this.entity.portalStatus = "Discarded";
                this.title = 'Information';
                this.msgInfo = 'Fund Request updated successfully!';
                this.infoModal1.show();
                this.discardModal.hide();
            }
        }, (err) => {
            console.log(err);
        });
    }
}