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
    public status: string = "";
    public clientId: string = "";
    public clientName: string = "";
    public total: number = 0;
    public totalPage = [];
    public lastPage: number = 0;
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
            createdDate: {
                title: 'Create By',
                type: 'number',
                filter: false
            }
        },
        pager: {
            display: true,
            perPage: 10
        }
    };


    constructor(private pro: ScheduleProvider) { }

    ngOnInit() {
        let user = JSON.parse(localStorage.getItem("CURRENT_TOKEN"));
        this.clientId = user.clientId;
        this.clientName = user.clientName;

        this.search();
    }

    public search() {
        let x = {
            filter: {
                client: this.clientId,
                clientAccount: "",
                scheduleStatus: ""
            },
            page: 1,
            size: 5,
            sort: [
                {
                    direction: "DESC",
                    field: "schedule_date__c"
                }
            ]
        }
        this.pro.search(x).subscribe((rsp: any) => {
            console.log(rsp);
            if (rsp.status === HTTP.STATUS_SUCCESS) {
                this.data = rsp.result.data;
                this.total = rsp.result.total;
                if (this.total % 10 == 0) {
                    for (let i = 1; i <= this.total / 5; i++) {
                        this.totalPage.push(i);
                    }
                    this.lastPage = parseInt((this.total / 5).toString());
                }
                else {
                    for (let i = 1; i <= (this.total / 5) + 1; i++) {
                        this.totalPage.push(i);
                    }
                    this.lastPage = parseInt((this.total / 5 + 1).toString());
                }
            }
            else {
            }
        }, (err) => {
            console.log(err);
        });
    }
}
