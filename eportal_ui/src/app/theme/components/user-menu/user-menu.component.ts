import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { Utils, Token } from 'app/utilities';
import { UserProvider, } from 'app/providers';

@Component({
    selector: 'app-user-menu',
    templateUrl: './user-menu.component.html',
    styleUrls: ['./user-menu.component.scss'],
    encapsulation: ViewEncapsulation.None
})

export class UserMenuComponent implements OnInit {
    public name = "";

    constructor(private pro: UserProvider) { }

    ngOnInit() {
        let user = Token.getUser();
        let salutation = user.salutation == null ? "" : user.salutation;
        let firstName = user.firstName == null ? "" : user.firstName;
        let lastName = user.lastName == null ? "" : user.lastName;
        this.name = salutation + " " + firstName + lastName;
    }

    public signOut() {
        this.pro.signOut();
    }
}