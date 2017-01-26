import {Routes} from '@angular/router';
import {FrontOfficeComponent} from "./front-office/front-office.component";
import {BackOfficeComponent} from "./back-office/back-office.component";
import {DashboardComponent} from "./dashboard/dashboard.component";
import {AdminArticleListComponent} from "./admin-article-list/admin-article-list.component";

export const ROUTES: Routes = [
  {
    path: '', component: FrontOfficeComponent
  },
  {
    path: 'admin', component: BackOfficeComponent, children: [
    {
      path: '', component: DashboardComponent
    },
    {
      path: 'articles', component: AdminArticleListComponent
    }
  ]
  }
];
