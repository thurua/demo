import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { Router } from '@angular/router';

@Component({
    selector: 'app-not-found',
    templateUrl: './not-found.component.html',
    styleUrls: ['./not-found.component.scss'],
    encapsulation: ViewEncapsulation.None
})

export class NotFoundComponent implements OnInit {
    router: Router;

    constructor(router: Router) {
        this.router = router;
    }

    ngOnInit() { }

    ngAfterViewInit() {
        document.getElementById('preloader').classList.add('hide');
    }

    public searchResult(): void {
        this.router.navigate(['pages/search']);
    }
}