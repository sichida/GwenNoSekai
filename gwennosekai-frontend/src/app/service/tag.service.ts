import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs';

@Injectable()
export class TagService {

  constructor(private http: Http) {
  }

  query(query: string): Observable<Response> {
    return this.http.get(`api/v1/tags?q=${query}`).map(res => res.json());
  }
}
