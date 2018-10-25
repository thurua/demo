import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FileUploadModule } from 'ng2-file-upload';
import { PerfectScrollbarModule } from 'ngx-perfect-scrollbar';
import { PERFECT_SCROLLBAR_CONFIG } from 'ngx-perfect-scrollbar';
import { PerfectScrollbarConfigInterface } from 'ngx-perfect-scrollbar';
import { Ng2SmartTableModule } from 'ng2-smart-table';
import { TabsModule } from 'ngx-bootstrap/tabs';
import { BsDatepickerModule } from 'ngx-bootstrap';
import { NgxUploaderModule } from 'ngx-uploader';
import { CollapseModule } from 'ngx-bootstrap/collapse';

const DEFAULT_PERFECT_SCROLLBAR_CONFIG: PerfectScrollbarConfigInterface = {
    suppressScrollX: true
};
import { routing } from './pages.routing';
import { PagesComponent } from './pages.component';
import { HeaderComponent } from '../theme/components/header/header.component';
import { FooterComponent } from '../theme/components/footer/footer.component';
import { SidebarComponent } from '../theme/components/sidebar/sidebar.component';
import { VerticalMenuComponent } from '../theme/components/menu/vertical-menu/vertical-menu.component';
import { HorizontalMenuComponent } from '../theme/components/menu/horizontal-menu/horizontal-menu.component';
import { BreadcrumbComponent } from '../theme/components/breadcrumb/breadcrumb.component';
import { BackTopComponent } from '../theme/components/back-top/back-top.component';
import { UserMenuComponent } from '../theme/components/user-menu/user-menu.component';
import { BlankComponent } from './blank/blank.component';
import { FileComponent } from './file/file.component';
import { SearchComponent } from './search/search.component';
import { ProfileComponent } from './profile/profile.component';
import { ModalModule } from 'ngx-bootstrap/modal';
import { FormsModule } from '@angular/forms';
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
import { FundRequestComponent } from './fund-request/fund-request.component';
import { AddFundRequestComponent } from './add-fund-request/add-fund-request.component';
import { FundAvailabilityComponent } from './fund-availability/fund-availability.component';

@NgModule({
    imports: [
        CommonModule,
        PerfectScrollbarModule,
        routing,
        ModalModule.forRoot(),
        FormsModule,
        FileUploadModule,
        Ng2SmartTableModule,
        NgxUploaderModule,
        TabsModule.forRoot(),
        BsDatepickerModule.forRoot(),
        CollapseModule.forRoot()
    ],
    declarations: [
        PagesComponent,
        HeaderComponent,
        FooterComponent,
        SidebarComponent,
        VerticalMenuComponent,
        HorizontalMenuComponent,
        BreadcrumbComponent,
        BackTopComponent,
        UserMenuComponent,
        BlankComponent,
        FileComponent,
        SearchComponent,
        ProfileComponent,
        ScheduleComponent,
        AddScheduleComponent,
        ScheduleDetailsComponent,
        CreditNotesComponent,
        InvoicesComponent,
        CreditNotesDetailsComponent,
        InvoicesDetailsComponent,
        ClientAccountFactoringDetailsComponent,
        ClientAccountLctrDetailsComponent,
        ClientAccountComponent,
        ClientAccountLoanDetailsComponent,
        FundRequestComponent,
        AddFundRequestComponent,
        FundAvailabilityComponent
    ],
    providers: [
        {
            provide: PERFECT_SCROLLBAR_CONFIG,
            useValue: DEFAULT_PERFECT_SCROLLBAR_CONFIG
        }
    ]
})
export class PagesModule { }
