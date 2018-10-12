import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { InvoiceProvider } from '../../providers/invoice';
import { ReasonProvider } from '../../providers/reason';
import { ActivatedRoute, Params } from '@angular/router';
import { HTTP } from '../../utilities/const';

@Component({
    selector: 'app-invoices-details',
    templateUrl: './invoices-details.component.html',
    styleUrls: ['./invoices-details.component.scss'],
    encapsulation: ViewEncapsulation.None
})
export class InvoicesDetailsComponent implements OnInit {
    public dataReason = [];
    public loader = true; // check Reason Section Show
    public data  : any = {};

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
            recordType: {
                title: 'Record Type',
                type: 'string',
                filter: false
            },
            reason: {
                title: 'Reason',
                type: 'string',
                filter: false
            }
        }
    };
    constructor(
        private pro: InvoiceProvider,
        private act: ActivatedRoute,
        private reas: ReasonProvider) {
    }

    ngOnInit() {
        this.seachById();
    }

    public seachById() {
        document.getElementById('preloader').style.display = 'block';

        let id = 0;
        this.act.params.subscribe((params: Params) => {
            id = params["_id"];
        });

        this.pro.getById(id).subscribe((rsp: any) => {
            if (rsp.status === HTTP.STATUS_SUCCESS) {
                this.data = rsp.result;
                this.getReasonBySfId(rsp.result.sfId);
            } else {
                let msg = rsp.message;
            }
        });

        setTimeout(function () {
            document.getElementById('preloader').style.display = 'none';
        }, 500); 
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
