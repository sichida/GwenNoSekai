import { Injectable } from '@angular/core';
import { Article } from '../shared/article.entity';
import { Http, Headers, RequestOptions, URLSearchParams } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { ApiResponse } from '../shared/api-response.entity';

@Injectable()
export class ArticleService {
  private _headers: Headers;

  constructor(private _http: Http) {
    this._headers = new Headers({'Content-Type': 'application/json'});
  }

  create(article: Article): Observable<Article> {
    let options = new RequestOptions({headers: this._headers});

    return this._http.post('api/v1/article', JSON.stringify(article), options)
      .map(res => res.json())
      .map(this.convertArticleDate);
  }

  query(page: number, size: number): Observable<ApiResponse<Array<Article>>> {
    let params: URLSearchParams = new URLSearchParams();
    params.set('page', page.toString());
    params.set('size', size.toString());
    let options = new RequestOptions({headers: this._headers, search: params});

    return this._http.get('api/v1/article', options)
      .map(res => res.json())
      .map(res => {
        res.content.forEach(this.convertArticleDate);
        return res
      });
  }

  private convertArticleDate(article) {
    article.creationDate = new Date(article.creationDate);
    return article;
  }
}
