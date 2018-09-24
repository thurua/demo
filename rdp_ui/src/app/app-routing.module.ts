import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes, Router, NavigationEnd } from '@angular/router';

import { HomeComponent } from './views/home/home.component';
import { ResSignupComponent } from './views/res-signup/res-signup.component';
import { ResWelcomeComponent } from './views/res-welcome/res-welcome.component';
import { BizSignupComponent } from './views/biz-signup/biz-signup.component';
import { BizWelcomeComponent } from './views/biz-welcome/biz-welcome.component';
import { ResPromoComponent } from './views/res-promo/res-promo.component';

declare let ga: Function;

const routes: Routes = [
    { path: '', component: HomeComponent, pathMatch: 'full' },
    { path: 'residential', component: HomeComponent },
    { path: 'residential/:planName', component: ResSignupComponent },
    { path: ':planName', component: ResPromoComponent },
    { path: 'residential/thankyou/:factSheet/:salesAgreement/:webReferenceNo', component: ResWelcomeComponent },
    { path: 'promo/thankyou/:factSheet/:salesAgreement/:webReferenceNo', component: ResWelcomeComponent },
    { path: 'business/signup', component: BizSignupComponent },
    { path: 'business/thankyou', component: BizWelcomeComponent },
    { path: '**', redirectTo: 'residential', pathMatch: 'full' }
];

@NgModule({
    imports: [
        CommonModule,
        RouterModule.forRoot(routes, { useHash: true })
    ],
    exports: [RouterModule],
    declarations: []
})

export class AppRoutingModule {
    constructor(public router: Router) {
        this.router.events.subscribe(event => {
            if (event instanceof NavigationEnd) {
                if (typeof ga === 'function') {
                    ga('gtm1.set', 'page', event.urlAfterRedirects);
                    ga('gtm1.send', 'pageview');
                }
            }
        });
    }
}