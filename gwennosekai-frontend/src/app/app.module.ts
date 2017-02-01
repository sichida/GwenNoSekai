import {BrowserModule} from "@angular/platform-browser";
import {NgModule} from "@angular/core";
import {FormsModule} from "@angular/forms";
import {HttpModule} from "@angular/http";
import {RouterModule} from "@angular/router";
import {ROUTES} from "./app.routes";
import {AppComponent} from "./app.component";
import {HeaderComponent} from "./common/header/header.component";
import {MenuComponent} from "./common/menu/menu.component";
import {PostListComponent} from "./front-office/post-list/post-list.component";
import {PostComponent} from "./front-office/post-list/post/post.component";
import {LogoComponent} from "./common/logo/logo.component";
import {PostService} from "./post.service";
import {FrontOfficeComponent} from "./front-office/front-office.component";
import {BackOfficeComponent} from "./back-office/back-office.component";
import {DashboardComponent} from "./back-office/dashboard/dashboard.component";
import {AdminMenuComponent} from "./back-office/admin-menu/admin-menu.component";
import {AdminArticleListComponent} from "./back-office/admin-article-list/admin-article-list.component";
import {AdminArticleComponent} from "./back-office/admin-article/admin-article.component";
import {ArticleService} from "./service/article.service";

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    MenuComponent,
    PostListComponent,
    PostComponent,
    LogoComponent,
    FrontOfficeComponent,
    BackOfficeComponent,
    DashboardComponent,
    AdminMenuComponent,
    AdminArticleListComponent,
    AdminArticleComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    RouterModule.forRoot(ROUTES, { useHash: true })
  ],
  providers: [
    ArticleService,
    PostService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
