import { Component, OnInit } from '@angular/core';

@Component({
    selector: 'app-biz-welcome',
    templateUrl: './biz-welcome.component.html',
    styleUrls: ['./biz-welcome.component.css']
})

export class BizWelcomeComponent implements OnInit {
    constructor() { }

    ngOnInit() {
        document.getElementById("welcomeDiv").focus();
    }
}