import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { ScheduleProvider } from 'app/providers/schedule';
import { HTTP } from '../../utilities/utility';

@Component({
    selector: 'app-schedule',
    templateUrl: './schedule.component.html',
    styleUrls: ['./schedule.component.scss'],
    encapsulation: ViewEncapsulation.None
})
export class ScheduleComponent implements OnInit {
    public portalStatus: string = "";
    public clientId: string = "";
    public clientName: string = "";
    public lstCA: any[] = [];
    public lstStatus: any[] = [];
    public clientAccountId = "";
    public total: number = 0;
    public pageSize = 5;
    public curentPage = 1;
    public pager: any = {};
    public pagedItems: any[];
    public data = [];
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
            scheduleNo: {
                title: 'Schedule Of Offer',
                type: 'string',
                filter: false
            },
            clientAccount: {
                title: 'Client Account No.',
                type: 'string',
                filter: false
            },
            scheduleDate: {
                title: 'Schedule Date',
                type: 'string',
                filter: false
            },
            documentType: {
                title: 'Document Type',
                type: 'string',
                filter: false
            },
            scheduleStatus: {
                title: 'Status',
                type: 'number',
                filter: false
            },
            createdBy: {
                title: 'Create By',
                type: 'number',
                filter: false
            }
        }
    };


    constructor(private pro: ScheduleProvider) { }

    ngOnInit() {
        let user = JSON.parse(localStorage.getItem("CURRENT_TOKEN"));
        this.clientId = user.clientId;
        this.clientName = user.clientName;
        this.searchCA();

        let tmpStatus = {
            data: [{
                code: "",
                value: "-- Please Select --"
            }, {
                code: "PEND",
                value: "Pending Authorization"
            }, {
                code: "AUTH",
                value: "Authorized"
            }, {
                code: "SUBM",
                value: "Submitted"
            }]
        }

        this.lstStatus = tmpStatus.data;
    }

    public searchClick(page: any) {
        this.search(page);
        this.curentPage = page;
    }

    public resetClick() {
        this.portalStatus = "";
        this.clientAccountId = "";
        this.data=[];
    }

    public search(page: any) {
        let x = {
            filter: {
                client: this.clientId,
                clientAccount: this.clientAccountId,
                portalStatus: this.portalStatus
            },
            page: page,
            size: this.pageSize,
            sort: [
                {
                    direction: "DESC",
                    field: "schedule_date__c"
                }
            ]
        }
        this.pro.search(x).subscribe((rsp: any) => {
            if (rsp.status === HTTP.STATUS_SUCCESS) {
                this.data = rsp.result.data;
                this.total = rsp.result.total;
                this.setPage(page);
                console.log(rsp);
            }
        }, (err) => {
            console.log(err);
        });
    }

    public searchCA() {
        let x = {
            filter: {
                client: this.clientId
            },
            page: 1,
            size: 20
        }

        this.pro.searchCA(x).subscribe((rsp: any) => {
            if (rsp.status === HTTP.STATUS_SUCCESS) {
                let item = {
                    sfid: "",
                    clientAccount: "-- Please select --"
                }
                rsp.result.data.unshift(item);
                this.lstCA = rsp.result.data;
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
