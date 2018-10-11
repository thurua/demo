import { Component, OnInit, ViewEncapsulation } from '@angular/core';

@Component({
    selector: 'app-dashboard',
    templateUrl: './dashboard.component.html',
    encapsulation: ViewEncapsulation.None
})

export class DashboardComponent implements OnInit {
    constructor() { }

    ngOnInit() { 
        document.getElementById('preloader').style.display = 'block';
    }

    ngAfterViewInit() {
        document.getElementById('preloader').style.display = 'none';
    }
}