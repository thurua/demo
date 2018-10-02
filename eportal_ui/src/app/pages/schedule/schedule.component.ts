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
    public isCollapsed: boolean = true;
    public statusOfSchedule: string = "";
    public clientId: string = "";
    public clientName: string = "";
    public lstCA: any[] = [];
    public clientAccountId = "";
    public total: number = 0;
    public totalPage = [];
    public lastPage: number = 0;
    public pageSize = 5;
    public curentPage = 1;
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

        this.search(1);
        this.curentPage = 1;
        this.searchCA();
    }

    public searchClick(page: any) {
        this.search(page);
        this.curentPage = page;
    }

    public search(page: any) {
        let x = {
            filter: {
                client: this.clientId,
                clientAccount: this.clientAccountId,
                scheduleStatus: this.statusOfSchedule
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
                this.totalPage = [];
                if (this.total % this.pageSize == 0) {
                    for (let i = 1; i <= this.total / this.pageSize && i < 4; i++) {
                        this.totalPage.push(i);
                    }
                    this.lastPage = parseInt((this.total / this.pageSize).toString());
                }
                else {
                    for (let i = 1; i <= (this.total / this.pageSize) + 1 && i < 4; i++) {
                        this.totalPage.push(i);
                    }
                    this.lastPage = parseInt((this.total / this.pageSize + 1).toString());
                }
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
                    id: null,
                    clientAccount: "-- Please select --"
                }
                rsp.result.data.unshift(item);

                this.lstCA = rsp.result.data;
            }
        }, (err) => {
            console.log(err);
        });
    }

    public nextClick() {
        this.search(this.curentPage + 1);
        this.curentPage++;
    }

    public firstPageClick() {
        this.search(1);
        this.curentPage = 1;
    }

    public lastPageClick() {
        this.search(this.lastPage);
        this.curentPage = this.lastPage;
    }
}
