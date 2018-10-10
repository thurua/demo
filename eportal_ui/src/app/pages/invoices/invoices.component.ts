import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { HTTP, Utils } from '../../utilities/utility';
import { ScheduleProvider } from '../../providers/schedule';
import { InvoiceProvider } from '../../providers/invoice';
import { CommonProvider } from '../../providers/common';

@Component({
    selector: 'app-invoices',
    templateUrl: './invoices.component.html',
    styleUrls: ['./invoices.component.scss',
        '../../../scss/vendors/bs-datepicker/bs-datepicker.scss'],
    encapsulation: ViewEncapsulation.None
})
export class InvoicesComponent implements OnInit {
    public clientName: string = "";
    public clientAccountId: string = "";
    public status: string = "";
    public scheduleNo: string = "";
    public documentType: string = "";
    public customer: string = "";
    public supplier: string = "";
    public invoiceNo: string = "";
    public clientId: string = "";
    public fromDate = new Date();
    public toDate = new Date();
    public maxDate = new Date();
    public lstDocumentType: any[] = [];
    public lstStatus: any[] = [];
    public lstCA: any[] = [];
    public lstCU: any[] = [];
    public lstSU: any[] = [];
    public data = [];
    public total: number = 0;
    public pageSize = 10;
    public curentPage = 1;
    public pager: any = {};
    public pagedItems: any[];
    public isCollapsed: boolean = false;
    public minDate = new Date();

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
                title: 'Invoice No.',
                filter: false,
                type: 'html',
                valuePrepareFunction: (cell, row) => {

                    return `<a href="/#/pages/invoices-details/${row.sfId}">${row.name}</a>`
                },
            },
            scheduleOfOffer: {
                title: 'Schedule No.',
                type: 'string',
                filter: false
            },
            clientAccount: {
                title: 'Client Account No.',
                type: 'string',
                filter: false
            },
            documentType: {
                title: 'Document Type',
                type: 'string',
                filter: false
            },
            customer: {
                title: 'Customer / Supplier',
                type: 'string',
                filter: false
            },
            invoiceDate: {
                title: 'Invoice Date',
                type: 'date',
                filter: false
            },
            invoiceAmount: {
                title: 'Invoice Amount',
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
                valuePrepareFunction: (value) => { return this.utl.formatDate(value, 'dd-MMM-yyyy HH:mm:ss') },
                filter: false
            }
        }
    };

    constructor(
        private pro: ScheduleProvider,
        private utl: Utils,
        private inv: InvoiceProvider,
        private com: CommonProvider) { }

    ngOnInit() {
        this.fromDate = this.utl.addMonths(this.fromDate, -6);
        this.minDate = this.utl.addMonths(this.minDate, -12);
        let user = JSON.parse(localStorage.getItem("CURRENT_TOKEN"));
        this.clientId = user.clientId;
        this.clientName = user.clientName;
        this.searchCA();
        this.searchSU();
        this.searchClick(1);

        let tmpStatus = {
            data: [{
                code: "",
                value: "-- Please Select --"
            }, {
                code: "Accepted",
                value: "Accepted"
            }, {
                code: "Disputed",
                value: "Disputed"
            }, {
                code: "Reassigned",
                value: "Reassigned"
            }, {
                code: "Rejected",
                value: "Rejected"
            }, {
                code: "Reversed",
                value: "Reversed"
            }, {
                code: "Unfunded",
                value: "Unfunded"
            }]
        }
        this.lstStatus = tmpStatus.data;

        let tmpDocumentType = {
            data: [{
                code: "",
                value: "-- Please Select --"
            }, {
                code: "Cash Disbursement",
                value: "Cash Disbursement"
            }, {
                code: "Invoice",
                value: "Invoice"
            }]
        }
        this.lstDocumentType = tmpDocumentType.data;
    }

    public resetClick() {
        this.clientId = "";
        this.clientAccountId = "";
        this.status = "";
        this.scheduleNo = "";
        this.invoiceNo = "";
        this.documentType = "";
        this.customer = "";
        this.supplier = "";

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

    // Search Client Account
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

        this.com.searchCA(x).subscribe((rsp: any) => {
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

    // Search Customer
    public searchCU(event: any) {
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

        this.com.searchCU(x).subscribe((rsp: any) => {
            let item = {
                sfid: "",
                name: "-- Please select --"
            }

            if (rsp.status === HTTP.STATUS_SUCCESS) {

                rsp.result.data.unshift(item);
                this.lstCU = rsp.result.data;

            }
            else {
                this.lstCU.unshift(item);
            }
        }, (err) => {
            console.log(err);
        });
    }

    // Search Customer
    public searchSU() {
        let x = {
            filter: {
                client: this.clientId
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

        this.com.searchSU(x).subscribe((rsp: any) => {
            let item = {
                sfid: "",
                name: "-- Please select --"
            }

            if (rsp.status === HTTP.STATUS_SUCCESS) {

                rsp.result.data.unshift(item);
                this.lstSU = rsp.result.data;
            }
            else {
                this.lstSU.unshift(item);
            }
        }, (err) => {
            console.log(err);
        });
    }

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
                status: this.status,
                scheduleNo: "%" + this.scheduleNo + "%",
                invoiceNo: "%" + this.invoiceNo + "%",
                documentType: this.documentType,
                customer: this.customer,
                supplier: this.supplier,
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

        this.inv.search(x).subscribe((rsp: any) => {
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

