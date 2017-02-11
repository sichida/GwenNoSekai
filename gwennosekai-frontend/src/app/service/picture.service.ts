import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { AbstractHttpService } from './abstract-http.service';
import { Picture } from '../shared/picture.entity';

@Injectable()
export class PictureService extends AbstractHttpService {

  constructor(private http: Http) {
    super();
  }

  upload(file: File): Observable<Picture> {
    let headers = new Headers();
    let formData: FormData = new FormData();
    formData.append('file', file, file.name);

    return this.http.post('api/v1/picture', formData, { headers }).map(res =>  res.json());
  }

  findById(pictureId: string): Observable<Picture> {
    let options = new RequestOptions({ headers: this._headers });

    return this.http.get(`api/v1/picture/${pictureId}`, options).map(res => res.json());
  }
}
