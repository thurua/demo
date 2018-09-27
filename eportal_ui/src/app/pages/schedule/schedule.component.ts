import { Component, OnInit, ViewEncapsulation } from '@angular/core';

@Component({
    selector: 'app-schedule',
    templateUrl: './schedule.component.html',
    styleUrls: ['./schedule.component.scss'],
    encapsulation: ViewEncapsulation.None
})
export class ScheduleComponent implements OnInit {
    public isCollapsed: boolean = true;

    constructor() { }

    ngOnInit() {
    }

}
