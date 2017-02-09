import { Headers } from '@angular/http';

export abstract class AbstractHttpService {
  protected _headers: Headers;

  constructor() {
    this._headers = new Headers({ 'Content-Type': 'application/json' });
  }
}
