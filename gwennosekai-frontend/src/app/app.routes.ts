import { Routes } from '@angular/router';
import { FrontOfficeComponent } from "./front-office/front-office.component";
import { BackOfficeComponent } from "./back-office/back-office.component";
import { DashboardComponent } from "./back-office/dashboard/dashboard.component";
import { AdminArticleListComponent } from "./back-office/admin-article-list/admin-article-list.component";
import { AdminArticleComponent } from "./back-office/admin-article/admin-article.component";

export const ROUTES: Routes = [
  {
    path: '', component: FrontOfficeComponent
  },
  {
    path: 'admin', component: BackOfficeComponent, children: [
    { path: '', component: DashboardComponent },
    { path: 'article/new', component: AdminArticleComponent },
    { path: 'article', component: AdminArticleListComponent },
    { path: 'article/:id', component: AdminArticleComponent }
  ]
  }
];
