import { Injectable } from '@angular/core';
import { Article } from '../shared/article.entity';
import { Http, Headers, RequestOptions, URLSearchParams } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { ApiResponse } from '../shared/api-response.entity';
import { AbstractHttpService } from './abstract-http.service';

@Injectable()
export class ArticleService extends AbstractHttpService {

  constructor(private http: Http) {
    super();
  }

  create(article: Article): Observable<Article> {
    let options = new RequestOptions({ headers: this._headers });

    return this.http.post('api/v1/article', JSON.stringify(article), options)
      .map(res => res.json())
      .map(this.convertArticleDate);
  }

  query(page: number, size: number): Observable<ApiResponse<Array<Article>>> {
    let params: URLSearchParams = new URLSearchParams();
    params.set('page', page.toString());
    params.set('size', size.toString());
    let options = new RequestOptions({ headers: this._headers, search: params });

    return this.http.get('api/v1/article', options)
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

  get(id: string) {
    let options = new RequestOptions({ headers: this._headers });
    return this.http.get(`api/v1/article/${id}`, options)
      .map(res => res.json())
      .map(this.convertArticleDate);
  }

  update(article: Article) {
    let options = new RequestOptions({ headers: this._headers });
    return this.http.put(`api/v1/article/${article.id}`, article, options)
      .map(res => res.json())
      .map(this.convertArticleDate);
  }
}
