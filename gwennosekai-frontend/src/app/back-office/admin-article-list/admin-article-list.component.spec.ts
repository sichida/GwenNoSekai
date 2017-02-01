/* tslint:disable:no-unused-variable */
import { RouterTestingModule } from "@angular/router/testing";
import { async, inject, ComponentFixture, TestBed } from "@angular/core/testing";
import { BaseRequestOptions, Http, HttpModule, Response, ResponseOptions } from "@angular/http";
import { MockBackend } from "@angular/http/testing";
import { Article } from "../../shared/article.entity";
import { ArticleService } from "../../service/article.service";
import { By } from '@angular/platform-browser';
import { DebugElement } from '@angular/core';

import { AdminArticleListComponent } from './admin-article-list.component';
import { ApiResponse } from "../../shared/api-response.entity";

describe('AdminArticleListComponent', () => {
  let component: AdminArticleListComponent;
  let fixture: ComponentFixture<AdminArticleListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [AdminArticleListComponent],
      providers: [
        ArticleService,
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
        HttpModule
      ]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminArticleListComponent);
    component = fixture.componentInstance;
  });

  it('should create', async(inject(
    [ArticleService, MockBackend], (service: ArticleService, mockBackend) => {
      const articles: ApiResponse<Array<Article>> = {
        content: [
          {
            title: 'My first article',
            content: 'This is the story of my life',
            creationDate: new Date()
          },
          {
            title: 'My second article',
            content: 'This is the story of my wife',
            creationDate: new Date()
          },
          {
            title: 'My third article',
            content: 'Other story',
            creationDate: new Date()
          }
        ],
        "totalElements": 3,
        "totalPages": 1,
        "last": true,
        "size": 10,
        "number": 0,
        "sort": null,
        "first": true,
        "numberOfElements": 3
      };
      mockBackend.connections.subscribe(conn => {
        conn.mockRespond(new Response(new ResponseOptions({body: JSON.stringify(articles)})));
      });

      fixture.detectChanges();
      expect(component).toBeTruthy();
      expect(component.articles).toBeTruthy();
      expect(component.articles.content.length).toBe(3);
      expect(component.articles.number).toBe(0);
      expect(component.articles.totalElements).toBe(3);
      expect(component.currentPage).toBe(0);
      expect(component.pageSize).toBe(10);
    })));

  it('should paginate', async(inject(
    [ArticleService, MockBackend], (service: ArticleService, mockBackend) => {
      const articles: ApiResponse<Array<Article>> = {
        content: [
          {
            title: 'My first article',
            content: 'This is the story of my life',
            creationDate: new Date()
          },
          {
            title: 'My second article',
            content: 'This is the story of my wife',
            creationDate: new Date()
          },
          {
            title: 'My third article',
            content: 'Other story',
            creationDate: new Date()
          }
        ],
        "totalElements": 6,
        "totalPages": 1,
        "last": true,
        "size": 10,
        "number": 1,
        "sort": null,
        "first": true,
        "numberOfElements": 3
      };
      mockBackend.connections.subscribe(conn => {
        conn.mockRespond(new Response(new ResponseOptions({body: JSON.stringify(articles)})));
      });

      component.loadArticles(1, 3);

      expect(component.articles).toBeTruthy();
      expect(component.articles.content.length).toBe(3);
      expect(component.currentPage).toBe(1);
      expect(component.articles.totalElements).toBe(6);
    })));

  it('should change number of article to load per page', () => {
    expect(component.pageSize).toBe(10);
    component.onItemPerPageChange(20);
    expect(component.pageSize).toBe(20);
  });
});
