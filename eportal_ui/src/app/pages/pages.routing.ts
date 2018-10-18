import { Routes, RouterModule } from '@angular/router';
import { ModuleWithProviders } from '@angular/core';
import { PagesComponent } from './pages.component';
import { BlankComponent } from './blank/blank.component';
import { FileComponent } from './file/file.component';
import { SearchComponent } from './search/search.component';
import { ProfileComponent } from './profile/profile.component';
import { ScheduleComponent } from './schedule/schedule.component';
import { AddScheduleComponent } from './add-schedule/add-schedule.component';
import { ScheduleDetailsComponent } from './schedule-details/schedule-details.component';
import { CreditNotesComponent } from './credit-notes/credit-notes.component';
import { InvoicesComponent } from './invoices/invoices.component';
import { CreditNotesDetailsComponent } from './credit-notes-details/credit-notes-details.component';
import { InvoicesDetailsComponent } from './invoices-details/invoices-details.component';
import { ClientAccountComponent } from './client-account/client-account.component';
import { ClientAccountFactoringDetailsComponent } from './client-account-factoring-details/client-account-factoring-details.component';
import { ClientAccountLctrDetailsComponent } from './client-account-lctr-details/client-account-lctr-details.component';
import { ClientAccountLoanDetailsComponent } from './client-account-loan-details/client-account-loan-details.component';

export const routes: Routes = [
    {
        path: '',
        component: PagesComponent,
        children: [
            { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
            { path: 'dashboard', loadChildren: 'app/pages/dashboard/dashboard.module#DashboardModule', data: { breadcrumb: 'Dashboard' } },
            { path: 'blank', component: BlankComponent, data: { breadcrumb: 'Blank page' } },
            { path: 'file', component: FileComponent, data: { breadcrumb: 'file page' } },
            { path: 'profile', component: ProfileComponent, data: { breadcrumb: 'User Profile' } },
            { path: 'schedule', component: ScheduleComponent, data: { breadcrumb: 'Schedules' } },
            { path: 'search', component: SearchComponent, data: { breadcrumb: 'Search' } },
            { path: 'add-schedule', component: AddScheduleComponent, data: { breadcrumb: 'Schedules' } },
            { path: 'schedule-details/:_id', component: ScheduleDetailsComponent, data: { breadcrumb: 'Schedules Details' } },
            { path: 'credit-notes', component: CreditNotesComponent, data: { breadcrumb: 'Credit Notes' } },
            { path: 'invoices', component: InvoicesComponent, data: { breadcrumb: 'Invoices' } },
            { path: 'credit-notes-details/:_id', component: CreditNotesDetailsComponent, data: { breadcrumb: 'Credit Note Details' } },
            { path: 'invoices-details/:_id', component: InvoicesDetailsComponent, data: { breadcrumb: 'Invoices Details' } },
            { path: 'client-account', component: ClientAccountComponent, data: { breadcrumb: 'Client Accounts' } },
            { path: 'factoring-details/:_id', component: ClientAccountFactoringDetailsComponent, data: { breadcrumb: 'Factoring Details' } },
            { path: 'lctr-details/:_id', component: ClientAccountLctrDetailsComponent, data: { breadcrumb: 'LCTR Details' } },
            { path: 'loan-details/:_id', component: ClientAccountLoanDetailsComponent, data: { breadcrumb: 'Loan Details' } }
        ]
    }
];

export const routing: ModuleWithProviders = RouterModule.forChild(routes);