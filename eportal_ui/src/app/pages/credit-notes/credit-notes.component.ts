import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { HTTP } from '../../utilities/utility';
import { CreditNoteProvider } from '../../providers/credit-notes';
import { Utils } from 'app/utilities/utils';

@Component({
    selector: 'app-credit-notes',
    templateUrl: './credit-notes.component.html',
    styleUrls: ['./credit-notes.component.scss',
        '../../../scss/vendors/bs-datepicker/bs-datepicker.scss'],
    encapsulation: ViewEncapsulation.None
})
export class CreditNotesComponent implements OnInit {
    public portalStatus: string = "";
    public accountName: string = "";
    public clientId: string = "";
    public clientName: string = "";
    public pageSize = 10;
    public curentPage = 1;
    public total: number = 0;
    public lstCustomer: any[] = [];
    public lstStatus: any[] = [];
    public lstCA: any[] = [];
    public data = [];
    public clientAccountId = "";
    public customerId = "";
    public pagedItems: any[];
    public pager: any = {};
    public fromDate = new Date();
    public toDate = new Date();
    public minDate = new Date();
    public maxDate = new Date();
    public scheduleNo = "";
    public creditNoteNo = "";
    public isCollapsed: boolean = false;

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
            name: {
                title: 'Credit Note No.',
                filter: false,
                type: 'html',
                valuePrepareFunction: (cell, row) => {
                    return `<a href="/#/pages/credit-notes-details/${row.sfId}">${row.name}</a>`
                },
            },
            scheduleNo: {
                title: 'Schedule No.',
                type: 'string',
                filter: false
            },
            clientAccountNo: {
                title: 'Client Account No.',
                type: 'string',
                filter: false
            },
            accountName: {
                title: 'Customer',
                type: 'string',
                filter: false
            },
            creditNoteDate: {
                title: 'Credit Note Date',
                type: 'string',
                filter: false
            },
            creditAmount: {
                title: 'Credit Note Amount',
                type: 'string',
                filter: false
            },
            status: {
                title: 'Status',
                type: 'string',
                filter: false
            },
            createdBy: {
                title: 'Created By',
                type: 'string',
                filter: false
            },
            createdDate: {
                title: 'Created Date / Time',
                type: 'date',
                valuePrepareFunction: (value) => { return this.utl.formatDate(value, 'dd-MMM-yyyy  HH:mm:ss') },
                filter: false
            }
        }
    };

    constructor(
        private pro: CreditNoteProvider,
        private utl: Utils) {
    }

    ngOnInit() {
        this.fromDate = this.utl.addMonths(this.fromDate, -6);
        this.minDate = this.utl.addMonths(this.minDate, -12);

        let user = JSON.parse(localStorage.getItem("CURRENT_TOKEN"));

        this.clientId = user.clientId;
        this.clientName = user.clientName;
        this.searchCA();
        this.searchClick(1);

        let tmpStatus = {
            dataS: [{
                code: "",
                value: "-- Please Select --"
            }, {
                code: "Accepted",
                value: "Accepted"
            }, {
                code: "Reassigned",
                value: "Reassigned"
            }, {
                code: "Rejected",
                value: "Rejected"
            }, {
                code: "Reversed",
                value: "Reversed"
            }]
        }
        this.lstStatus = tmpStatus.dataS;
    }

    public resetClick() {
        this.portalStatus = "";
        this.clientAccountId = "";
        this.customerId = "";
        this.scheduleNo = "";
        this.creditNoteNo = "";
        this.data = [];

        // Reset Date
        let d = new Date();
        let d1 = this.utl.formatDate(this.utl.addMonths(d, -6), 'dd-MMM-yyyy');
        let d2 = this.utl.formatDate(this.fromDate, 'dd-MMM-yyyy');
        if (d1 != d2) {
            d = new Date();
            this.fromDate = this.utl.addMonths(d, -6);
        }

        d = new Date();
        d1 = this.utl.formatDate(d, 'dd-MMM-yyyy');
        d2 = this.utl.formatDate(this.toDate, 'dd-MMM-yyyy');

        if (d1 != d2) {
            d = new Date();
            this.toDate = d;
        }
    }

    // serach function
    public searchClick(page: any) {
        this.search(page);
        this.curentPage = page;
    }

    public search(page: any) {
        let fr = this.fromDate == null ? null : this.fromDate.toISOString();
        let to = this.toDate == null ? null : this.toDate.toISOString();

        let x = {
            filter: {
                client: this.clientId,
                clientAccount: this.clientAccountId,
                customer: this.customerId,
                status: this.portalStatus,
                scheduleNo: "%" + this.scheduleNo + "%",
                name: "%" + this.creditNoteNo + "%",
                frCreatedDate: fr,
                toCreatedDate: to
            },
            page: page,
            size: this.pageSize,
            sort: [
                {
                    direction: "DESC",
                    field: "createdDate"
                }
            ]
        }

        this.pro.search(x).subscribe((rsp: any) => {
            if (rsp.status === HTTP.STATUS_SUCCESS) {
                this.data = rsp.result.data;
                this.total = rsp.result.total;
                this.setPage(page);
            }
        }, (err) => {
            console.log(err);
        });

    }

    public setPage(page: number) {
        this.pager = this.getPager(this.total, page, this.pageSize);
        this.pagedItems = this.data.slice(this.pager.startIndex, this.pager.endIndex + 1);
    }

    public searchCA() {
        let x = {
            filter: {
                client: this.clientId,
                status: "Activated"
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

        this.pro.searchCA(x).subscribe((rsp: any) => {
            let itemCA = {
                sfid: "",
                clientAccount: "-- Please select --"
            }
            if (rsp.status === HTTP.STATUS_SUCCESS) {
                rsp.result.data.unshift(itemCA);
                this.lstCA = rsp.result.data;
            }
            else {
                this.lstCA.unshift(itemCA);
            }
        }, (err) => {
            console.log(err);
        });
    }

    public searchCustomer(event: any) {
        let x = {
            filter: {
                clientAccount: event.target.value
            },
            page: 1,
            size: 20,
            sort: [
                {
                    direction: "ASC",
                    field: "name"
                }
            ]
        }
        this.pro.searchCustomer(x).subscribe((rsp: any) => {
            let itemCM = {
                sfid: "",
                name: "-- Please select --"
            }
            if (rsp.status === HTTP.STATUS_SUCCESS) {
                rsp.result.data.unshift(itemCM);
                this.lstCustomer = rsp.result.data;
            }
            else {
                this.lstCustomer.unshift(itemCM);
            }
        }, (err) => {
            console.log(err);
        });
    }

    private getPager(totalItems: number, currentPage: number = 1, pageSize: number = 1) {
        let totalPages = Math.ceil(totalItems / pageSize);

        if (currentPage < 1) {
            currentPage = 1;
        } else if (currentPage > totalPages) {
            currentPage = totalPages;
        }

        let startPage: number, endPage: number;
        if (totalPages <= 10) {
            // less than 10 total pages so show all
            startPage = 1;
            endPage = totalPages;
        } else {
            // more than 10 total pages so calculate start and end pages
            if (currentPage <= 6) {
                startPage = 1;
                endPage = 10;
            } else if (currentPage + 4 >= totalPages) {
                startPage = totalPages - 9;
                endPage = totalPages;
            } else {
                startPage = currentPage - 5;
                endPage = currentPage + 4;
            }
        }

        // calculate start and end item indexes
        let startIndex = (currentPage - 1) * pageSize;
        let endIndex = Math.min(startIndex + pageSize - 1, totalItems - 1);

        // create an array of pages to ng-repeat in the pager control
        let pages = Array.from(Array((endPage + 1) - startPage).keys()).map(i => startPage + i);

        // return object with all pager properties required by the view
        return {
            totalItems: totalItems,
            currentPage: currentPage,
            pageSize: pageSize,
            totalPages: totalPages,
            startPage: startPage,
            endPage: endPage,
            startIndex: startIndex,
            endIndex: endIndex,
            pages: pages
        };
    }
}