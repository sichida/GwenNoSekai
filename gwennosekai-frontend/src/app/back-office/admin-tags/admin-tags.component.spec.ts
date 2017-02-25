/* tslint:disable:no-unused-variable */
import { async, inject, ComponentFixture, TestBed } from '@angular/core/testing';
import { BaseRequestOptions, Http, HttpModule, Response, ResponseOptions } from '@angular/http';
import { MockBackend } from '@angular/http/testing';
import { By } from '@angular/platform-browser';
import { DebugElement } from '@angular/core';
import { TagInputModule } from 'ng2-tag-input';
import { FormsModule } from "@angular/forms";
import { AdminTagsComponent } from './admin-tags.component';
import { TagService } from '../../service/tag.service';

describe('AdminTagsComponent', () => {
  let component: AdminTagsComponent;
  let fixture: ComponentFixture<AdminTagsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [AdminTagsComponent],
      providers: [
        TagService,
        MockBackend,
        BaseRequestOptions,
        {
          provide: Http,
          useFactory: (backend, options) => new Http(backend, options),
          deps: [MockBackend, BaseRequestOptions]
        }
      ],
      imports: [
        TagInputModule,
        FormsModule
      ]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminTagsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should find tag', inject(
    [MockBackend], (mockBackend) => {
      const mockTags: Array<string> = ['tag 1', 'tag 2', 'tag 3'];

      mockBackend.connections.subscribe(conn => {
        conn.mockRespond(new Response(new ResponseOptions({ body: JSON.stringify(mockTags) })));
      });

      component.requestAutocompleteItems('tag').subscribe(response => {
        expect(response).toEqual(mockTags);
      });
    }));
});
