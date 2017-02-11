/* tslint:disable:no-unused-variable */
import { TestBed, async, inject } from '@angular/core/testing';
import { BaseRequestOptions, Http, HttpModule, Response, ResponseOptions } from '@angular/http';
import { MockBackend } from '@angular/http/testing';
import { PictureService } from './picture.service';
import { Picture } from '../shared/picture.entity';

describe('PictureService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [
        PictureService,
        MockBackend,
        BaseRequestOptions,
        {
          provide: Http,
          useFactory: (backend, options) => new Http(backend, options),
          deps: [MockBackend, BaseRequestOptions]
        }
      ],
      imports: [HttpModule]
    });
  });

  it('should ...', inject([PictureService], (service: PictureService) => {
    expect(service).toBeTruthy();
  }));

  it('should upload a thumbnail', async(inject(
    [PictureService, MockBackend], (service: PictureService, mockBackend) => {
      const mockPicture: Picture = {
        id: 'some_random_id',
        location: '/pictures/image.jpg',
        filename: 'image.jpg'
      };

      mockBackend.connections.subscribe(conn => {
        conn.mockRespond(new Response(new ResponseOptions({ body: JSON.stringify(mockPicture) })));
      });

      let mockFile: any = { name: 'mockFile' };

      service.upload(mockFile).subscribe(res => {
        expect(res).toEqual(mockPicture);
      });
    })));

  it('should find an existing picture', async(inject(
    [PictureService, MockBackend], (service: PictureService, mockBackend) => {
      const mockPicture: Picture = {
        id: 'some_random_id',
        location: '/pictures/image.jpg',
        filename: 'image.jpg'
      };

      mockBackend.connections.subscribe(conn => {
        conn.mockRespond(new Response(new ResponseOptions({ body: JSON.stringify(mockPicture) })));
      });

      service.findById('some_random_id').subscribe(res => {
        expect(res).toEqual(mockPicture);
      });
    })));
});
