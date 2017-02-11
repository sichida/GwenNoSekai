import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Article } from '../../shared/article.entity';
import { ArticleService } from '../../service/article.service';
import { Picture } from '../../shared/picture.entity';
import { StringUtilsService } from '../../service/string-utils.service';

@Component({
  selector: 'app-admin-article',
  templateUrl: './admin-article.component.html',
  styleUrls: ['./admin-article.component.scss']
})
export class AdminArticleComponent implements OnInit {
  article: Article;
  created: boolean = false;
  updated: boolean = false;
  errorOccurred: boolean = false;

  constructor(private route: ActivatedRoute,
              private articleService: ArticleService,
              private stringUtils: StringUtilsService) {
  }

  ngOnInit() {
    if (this.route.snapshot.params['id']) {
      this.articleService.get(this.route.snapshot.params['id'])
        .subscribe(
          (article: Article) => this.article = article,
          () => this.errorOccurred = true);
    } else {
      this.article = new Article();
    }
  }

  submitArticle() {
    this.created = false;
    this.updated = false;
    if (!this.article.id) {
      this.articleService.create(this.article).subscribe(article => {
        this.article = article;
        this.created = true;
        setTimeout(() => this.created = false, 5000);
      }, () => this.errorOccurred = true);
    } else {
      this.articleService.update(this.article).subscribe(article => {
        this.article = article;
        this.updated = true;
        setTimeout(() => this.updated = false, 5000);
      }, () => this.errorOccurred = true);
    }
  }

  onTitleChanged(updatePermalink: boolean) {
    if (updatePermalink) {

      this.article.permalink = this.stringUtils.removeAccent(this.article.title)
        .trim()
        .toLowerCase()
        .replace(/[^a-zA-Z\d\s]/g, ' ')
        .replace(/\s\s+/g, ' ')
        .replace(/\s/g, '-');
    }
  }

  onThumbnailUploaded(mockPicture: Picture) {
    this.article.thumbnailId = mockPicture.id;
  }
}
