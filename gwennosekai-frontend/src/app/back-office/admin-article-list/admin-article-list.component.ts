import {Component, OnInit} from "@angular/core";
import {Article} from "../../shared/article.entity";
import {ArticleService} from "../../service/article.service";
import { ApiResponse } from '../../shared/api-response.entity';

@Component({
  selector: 'app-admin-article-list',
  templateUrl: './admin-article-list.component.html',
  styleUrls: ['./admin-article-list.component.scss']
})
export class AdminArticleListComponent implements OnInit {
  public articles: ApiResponse<Array<Article>>;
  currentPage: number = 0;
  pageSize: number = 10;

  constructor(private articleService: ArticleService) {
  }

  ngOnInit() {
    this.loadArticles(this.currentPage, this.pageSize);
  }

  loadArticles(page: number, size: number) {
    this.articleService.query(page, size)
      .subscribe(res => {
        this.articles = res;
        this.currentPage = this.articles.number;
      });
  }

  onItemPerPageChange(pageSize: number) {
    this.pageSize = pageSize;
    this.loadArticles(this.currentPage, this.pageSize);
  }
}
