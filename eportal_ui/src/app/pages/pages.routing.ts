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
            { path: 'schedule-details', component: ScheduleDetailsComponent, data: { breadcrumb: 'Schedules Details' } }
            
        ]
    }
];

export const routing: ModuleWithProviders = RouterModule.forChild(routes);