import { Component, OnInit, ViewEncapsulation } from '@angular/core';

@Component({
    selector: 'app-user-menu',
    templateUrl: './user-menu.component.html',
    styleUrls: ['./user-menu.component.scss'],
    encapsulation: ViewEncapsulation.None
})

export class UserMenuComponent implements OnInit {
    public name = '';
    constructor() { }

    ngOnInit() {
        let user = JSON.parse(localStorage.getItem("CURRENT_TOKEN"));
        let firstName = user.firstName == null ? '' : user.firstName + ' ';
        this.name = user.salutation + ' ' + firstName + user.lastName;
    }
}