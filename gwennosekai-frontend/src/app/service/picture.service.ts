import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { AbstractHttpService } from './abstract-http.service';
import { Picture } from '../shared/picture.entity';

@Injectable()
export class PictureService extends AbstractHttpService {

  constructor(private http: Http) {
    super();
  }

  uploadThumbnail(file: File): Observable<Picture> {
    let headers = new Headers();
    let formData: FormData = new FormData();
    formData.append('file', file, file.name);

    return this.http.post('api/v1/picture', formData, { headers }).map(res =>  res.json());
  }
}
