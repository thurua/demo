import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { CreditNoteProvider } from '../../providers/credit-notes';
import { ReasonProvider } from '../../providers/reason';
import { ActivatedRoute, Params } from '@angular/router';
import { HTTP } from '../../utilities/const';

@Component({
    selector: 'app-credit-notes-details',
    templateUrl: './credit-notes-details.component.html',
    styleUrls: ['./credit-notes-details.component.scss'],
    encapsulation: ViewEncapsulation.None
})
export class CreditNotesDetailsComponent implements OnInit {
    public dataReason = [];
    public data = {
        customerFromExcel: "",
        clientAccount: "",
        name: "",
        customer: "",
        currencyIsoCode: "",
        appliedInvoice: "",
        clientRemarks: "",
        opsRemarks: "",
        status: "",
        scheduleOfOffer: "",
        creditNoteDate: "",
        customerBranch: "",
        creditAmount: "",
        outstandingAmount: ""
    };

    public settings = {
        selectMode: 'single',  //single|multi
        hideHeader: false,
        hideSubHeader: false,
        actions: {
            add: false,
            edit: false,
            delete: false,
            custom: [],
        },
        handle: {
            editable: false
        },
        noDataMessage: 'No data found',
        columns: {
            sNo: {
                title: 'S.No',
                type: 'string',
                filter: false,
            },
            reason: {
                title: 'Reason',
                type: 'string',
                filter: false
            },
            date: {
                title: 'Date',
                type: 'string',
                filter: false
            },
            amount: {
                title: 'Amount',
                type: 'string',
                filter: false
            }
        }
    };
    constructor(
        private act: ActivatedRoute,
        private pro: CreditNoteProvider,
        private reas: ReasonProvider) {
    }

    ngOnInit() {
        this.seachById();
    }

    public seachById() {
        let id = 0;
        this.act.params.subscribe((params: Params) => {
            id = params["_id"];
        });

        this.pro.getById(id).subscribe((rsp: any) => {
            if (rsp.status === HTTP.STATUS_SUCCESS) {
                this.data = rsp.result;
                if (rsp.result.status == "Rejected") {
                }
                this.getReasonBySfId(rsp.result.sfId);
            } else {
                let msg = rsp.message;
            }
        });
    }

    public getReasonBySfId(sfId: String) {
        this.reas.getById(sfId).subscribe((rsp: any) => {
            if (rsp.status === HTTP.STATUS_SUCCESS) {
                this.dataReason = rsp.result;
                let i = 0;
                this.dataReason.forEach(element => {
                    i++;
                    element.sNo = i;
                });
            } else {
                let msg = rsp.message;
            }
        });
    }
}