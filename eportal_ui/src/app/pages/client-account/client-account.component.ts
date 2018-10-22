import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { HTTP, Utils, Token } from 'app/utilities';
import { CommonProvider, ClientAccountProvider } from 'app/providers';

@Component({
    selector: 'app-client-account',
    templateUrl: './client-account.component.html',
    styleUrls: ['./client-account.component.scss'],
    encapsulation: ViewEncapsulation.None
})
export class ClientAccountComponent implements OnInit {
    public isCollapsed: boolean = false;
    public clientId = "";
    public clientName = "";
    public lstStatus = [];
    public lstCA = [];
    public pageSize = 10;

    //Loan
    public clientAccountIdLoan = "";
    public statusLoan = "";
    public curentPageLoan = 1;
    public pagerLoan: any = {};
    public pagedItemsLoan: any[];
    public totalLoan: number = 0;
    public dataLoan = [];
    public settingLoan = {};

    //Factoring
    public clientAccountIdFactoring = "";
    public statusFactoring = "";
    public curentPageFactoring = 1;
    public pagerFactoring: any = {};
    public pagedItemsFactoring: any[];
    public totalFactoring: number = 0;
    public dataFactoring = [];
    public settingFactoring = {};

    //LCTR
    public clientAccountIdLCTR = "";
    public statusLCTR = "";
    public curentPageLCTR = 1;
    public pagerLCTR: any = {};
    public pagedItemsLCTR: any[];
    public totalLCTR: number = 0;
    public dataLCTR = [];
    public settingLCTR = {};

    constructor(private pro: ClientAccountProvider,
        private proCommon: CommonProvider) { }

    ngOnInit() {
        let user = Token.getUser();
        this.clientId = user.clientId;
        this.clientName = user.clientName;

        this.searchCA();
        this.searchClick(1, 'Loan');
        this.searchClick(1, 'Factoring');
        this.searchClick(1, 'LC/TR');
        this.setSetting();

        let tmpStatus = {
            data: [{
                code: "",
                value: "-- Please Select --"
            }, {
                code: "Activated",
                value: "Activated"
            }, {
                code: "Closed",
                value: "Closed"
            }, {
                code: "Suspended",
                value: "Suspended"
            }, {
                code: "Terminated",
                value: "Terminated"
            }]
        };

        this.lstStatus = tmpStatus.data;
    }

    public searchClick(page: any, recordType: String) {
        this.search(page, recordType);
        this.curentPageLoan = page;
    }

    public search(page: any, recordType: String) {
        document.getElementById('preloader').style.display = 'block';
        let status = '';
        let clientAccount = '';
        if (recordType == 'Loan') {
            status = this.statusLoan;
            clientAccount = this.clientAccountIdLoan;
        }
        if (recordType == 'Factoring') {
            status = this.statusFactoring;
            clientAccount = this.clientAccountIdFactoring;
        }
        if (recordType == 'LC/TR') {
            status = this.statusLCTR;
            clientAccount = this.clientAccountIdLCTR;
        }
        let x = {
            filter: {
                recordType: recordType,
                client: this.clientId,
                clientAccount: clientAccount,
                status: status
            },

            page: page,
            size: this.pageSize
        }
        this.pro.search(x).subscribe((rsp: any) => {
            if (rsp.status === HTTP.STATUS_SUCCESS) {
                if (recordType == 'Loan') {
                    this.dataLoan = rsp.result.data;
                    this.totalLoan = rsp.result.total;
                    this.setPageLoan(page);
                }
                if (recordType == 'Factoring') {
                    this.dataFactoring = rsp.result.data;
                    this.totalFactoring = rsp.result.total;
                    this.setPageFactoring(page);
                }
                if (recordType == 'LC/TR') {
                    this.dataLCTR = rsp.result.data;
                    this.totalLCTR = rsp.result.total;
                    this.setPageLCTR(page);
                }
            }
        }, (err) => {
            console.log(err);
        });

        setTimeout(function () {
            document.getElementById('preloader').style.display = 'none';
        }, 500);
    }

    public resetClick(recordType: String) {
        if (recordType == 'Loan') {
            this.clientAccountIdLoan = "";
            this.statusLoan = "";
        }
        if (recordType == 'Factoring') {
            this.clientAccountIdFactoring = "";
            this.statusFactoring = "";
        }
        if (recordType == 'LC/TR') {
            this.clientAccountIdLCTR = "";
            this.statusLCTR = "";
        }
        this.searchClick(1, recordType);
    }

    public setSetting() {
        let tmpData = {
            selectMode: 'single',
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
                scheduleNo: {
                    title: 'Client Account No.',
                    filter: false,
                    type: 'html',
                    valuePrepareFunction: (cell, row) => {
                        if (row.recordType == 'Loan') {
                            return `<a href="/#/pages/loan-details/${row.sfId}">${row.clientAccount}</a>`
                        }
                        if (row.recordType == 'Factoring') {
                            return `<a href="/#/pages/factoring-details/${row.sfId}">${row.clientAccount}</a>`
                        }
                        if (row.recordType == 'LC/TR') {
                            return `<a href="/#/pages/lctr-details/${row.sfId}">${row.clientAccount}</a>`
                        }
                    },
                },
                productTypeName: {
                    title: 'Product Type Name',
                    type: 'string',
                    filter: false
                },
                activatedOn: {
                    title: 'Activation Date',
                    type: 'date',
                    valuePrepareFunction: (value) => { return Utils.format(value, 'dd-MMM-yyyy') },
                    filter: false
                },
                status: {
                    title: 'Status',
                    type: 'string',
                    filter: false
                }
            }
        };

        this.settingLoan = tmpData;
        this.settingFactoring = tmpData;
        this.settingLCTR = tmpData;
    }

    public searchCA() {
        document.getElementById('preloader').style.display = 'block';
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

    public setPageLoan(page: number) {
        this.pagerLoan = this.getPager(this.totalLoan, page, this.pageSize);
        this.pagedItemsLoan = this.dataLoan.slice(this.pagerLoan.startIndex, this.pagerLoan.endIndex + 1);
    }

    public setPageFactoring(page: number) {
        this.pagerFactoring = this.getPager(this.totalFactoring, page, this.pageSize);
        this.pagedItemsFactoring = this.dataFactoring.slice(this.pagerFactoring.startIndex, this.pagerFactoring.endIndex + 1);
    }

    public setPageLCTR(page: number) {
        this.pagerLCTR = this.getPager(this.totalLCTR, page, this.pageSize);
        this.pagedItemsLCTR = this.dataLoan.slice(this.pagerLCTR.startIndex, this.pagerLCTR.endIndex + 1);
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
