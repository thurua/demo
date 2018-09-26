import { Routes, RouterModule } from '@angular/router';
import { ModuleWithProviders } from '@angular/core';
import { PagesComponent } from './pages.component';
import { BlankComponent } from './blank/blank.component';
import { FileComponent } from './file/file.component';
import { SearchComponent } from './search/search.component';
import { ProfileComponent } from './profile/profile.component';

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
            { path: 'search', component: SearchComponent, data: { breadcrumb: 'Search' } }
        ]
    }
];

export const routing: ModuleWithProviders = RouterModule.forChild(routes);