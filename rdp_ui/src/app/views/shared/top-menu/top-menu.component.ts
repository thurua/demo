import { Component, OnInit } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';

@Component({
    selector: 'app-top-menu',
    templateUrl: './top-menu.component.html',
    styleUrls: ['./top-menu.component.css']
})

export class TopMenuComponent implements OnInit {
    public strNull: string;

    constructor(private rou: Router) { }

    ngOnInit() {
        this.strNull = btoa("null");
    }

    public redirectLink() {
        window.open("http://corporate.reddotpower.com.sg", 'blank');
        //window.location.href = "https://www.reddotpower.com.sg";
    }
}