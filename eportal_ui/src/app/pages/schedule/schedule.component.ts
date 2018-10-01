import { Component, OnInit, ViewEncapsulation } from '@angular/core';

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

    constructor() { }

    ngOnInit() {
        let user = JSON.parse(localStorage.getItem("CURRENT_TOKEN"));
        this.clientId = user.clientId;
        this.clientName = user.clientName;
    }

}
