/* tslint:disable:no-unused-variable */
import { async, inject, ComponentFixture, TestBed } from '@angular/core/testing';
import { BaseRequestOptions, Http, HttpModule, Response, ResponseOptions } from '@angular/http';
import { MockBackend } from '@angular/http/testing';
import { FormsModule } from '@angular/forms';
import { RouterTestingModule } from '@angular/router/testing';
import { AdminThumbnailComponent } from './admin-thumbnail.component';
import { Picture } from '../../shared/picture.entity';
import { PictureService } from '../../service/picture.service';

describe('AdminThumbnailComponent', () => {
  let component: AdminThumbnailComponent;
  let fixture: ComponentFixture<AdminThumbnailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [AdminThumbnailComponent],
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
      imports: [
        RouterTestingModule,
        HttpModule,
        FormsModule
      ]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminThumbnailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create AdminThumbnailComponent', () => {
    expect(component).toBeTruthy();
  });

  it('should create a new article', inject(
    [MockBackend], (mockBackend) => {
      const mockPicture: Picture = {
        id: 'some_random_id'
      };

      mockBackend.connections.subscribe(conn => {
        conn.mockRespond(new Response(new ResponseOptions({ body: JSON.stringify(mockPicture) })));
      });
      let toUpload: any = {};

      component.pictureUploaded.subscribe(picture => {
        expect(picture).toEqual(mockPicture);
      });

      component.upload(toUpload);
      expect(component.picture).toEqual(mockPicture);
    }));
});
