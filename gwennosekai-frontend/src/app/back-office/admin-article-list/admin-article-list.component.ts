import {Component, OnInit} from "@angular/core";
import {Article} from "../../shared/article.entity";
import {ArticleService} from "../../service/article.service";

@Component({
  selector: 'app-admin-article-list',
  templateUrl: './admin-article-list.component.html',
  styleUrls: ['./admin-article-list.component.scss']
})
export class AdminArticleListComponent implements OnInit {
  public articles: Array<Article>;
  private static readonly FIRST_PAGE = 0;
  private static readonly DEFAULT_PAGE_SIZE = 10;

  constructor(private articleService: ArticleService) {
  }

  ngOnInit() {
    this.articleService.query(AdminArticleListComponent.FIRST_PAGE, AdminArticleListComponent.DEFAULT_PAGE_SIZE)
      .subscribe(res => this.articles = res);
  }
}
