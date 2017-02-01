import { Component, OnInit } from '@angular/core';
import {Article} from "../../shared/post.entity";
import {ArticleService} from "../../service/article.service";

@Component({
  selector: 'app-admin-article',
  templateUrl: './admin-article.component.html',
  styleUrls: ['./admin-article.component.css']
})
export class AdminArticleComponent implements OnInit {
  article: Article;

  constructor(private articleService:ArticleService) { }

  ngOnInit() {
    this.article = new Article();
  }

  submitArticle() {
    if (!this.article.id) {
      this.articleService.create(this.article).subscribe(article => this.article = article);
    }
  }
}
