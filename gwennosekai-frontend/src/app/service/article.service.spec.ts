/* tslint:disable:no-unused-variable */
import { TestBed, async, inject } from '@angular/core/testing';
import { BaseRequestOptions, Http, HttpModule, Response, ResponseOptions } from '@angular/http';
import { MockBackend } from '@angular/http/testing';
import { ArticleService } from './article.service';
import { Article } from '../shared/article.entity';
import { ApiResponse } from '../shared/api-response.entity';

describe('ArticleService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
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
      imports: [HttpModule]
    });
  });

  it('should ...', inject([ArticleService], (service: ArticleService) => {
    expect(service).toBeTruthy();
  }));

  it('should create a new article', async(inject(
    [ArticleService, MockBackend], (service, mockBackend) => {
      const mockArticle: Article = {
        title: 'My first article',
        content: 'This is the story of my life',
        creationDate: new Date()
      };

      mockBackend.connections.subscribe(conn => {
        conn.mockRespond(new Response(new ResponseOptions({ body: JSON.stringify(mockArticle) })));
      });

      service.create({ title: 'My first article', content: 'This is the story of my life' })
        .subscribe(res => {
          expect(res).toEqual(mockArticle);
        });
    })));

  it('should get all articles', async(inject(
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
        "totalElements": 1,
        "totalPages": 1,
        "last": true,
        "size": 10,
        "number": 0,
        "sort": null,
        "first": true,
        "numberOfElements": 1
      };

      mockBackend.connections.subscribe(conn => {
        conn.mockRespond(new Response(new ResponseOptions({ body: JSON.stringify(articles) })));
      });

      service.query(0, 3)
        .subscribe(res => {
          expect(res).toEqual(articles);
        });
    })));

  it('should get one article with it id', async(inject(
    [ArticleService, MockBackend], (service: ArticleService, mockBackend) => {
      const article: Article =
        {
          id: 'some_random_id',
          title: 'My first article',
          content: 'This is the story of my life',
          creationDate: new Date()
        };

      mockBackend.connections.subscribe(conn => {
        conn.mockRespond(new Response(new ResponseOptions({ body: JSON.stringify(article) })));
      });

      service.get('some_random_id')
        .subscribe(res => {
          expect(res).toEqual(article);
        });
    })));

  it('should update an existing article', async(inject(
    [ArticleService, MockBackend], (service: ArticleService, mockBackend) => {
      const mockArticle: Article = {
        id: 'some_random_id',
        title: 'My first article',
        content: 'This is the story of my life',
        creationDate: new Date()
      };

      mockBackend.connections.subscribe(conn => {
        conn.mockRespond(new Response(new ResponseOptions({ body: JSON.stringify(mockArticle) })));
      });

      service.update(mockArticle).subscribe(res => {
        expect(res).toEqual(mockArticle);
      });
    })));
})
;
