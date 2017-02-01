import {Injectable} from '@angular/core';
import {Article} from '../shared/post.entity';
import {Http, Headers, RequestOptions } from "@angular/http";
import {Observable} from 'rxjs/Rx';

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
  }
}
