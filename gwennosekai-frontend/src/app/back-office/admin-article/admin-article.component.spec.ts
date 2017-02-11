/* tslint:disable:no-unused-variable */
import { RouterTestingModule } from '@angular/router/testing';
import { async, inject, ComponentFixture, TestBed } from '@angular/core/testing';
import { BaseRequestOptions, Http, HttpModule, Response, ResponseOptions } from '@angular/http';
import { MockBackend } from '@angular/http/testing';
import { FormsModule } from '@angular/forms';
import { AdminArticleComponent } from './admin-article.component';
import { Article } from '../../shared/article.entity';
import { ArticleService } from '../../service/article.service';
import { AdminThumbnailComponent } from '../admin-thumbnail/admin-thumbnail.component';
import { PictureService } from '../../service/picture.service';
import { Picture } from '../../shared/picture.entity';
import { RichTextEditorComponent } from '../rich-text-editor/rich-text-editor.component';

declare var tinymce: TinyMCE.Static;

describe('AdminArticleComponent', () => {
  let component: AdminArticleComponent;
  let fixture: ComponentFixture<AdminArticleComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [
        AdminArticleComponent,
        AdminThumbnailComponent,
        RichTextEditorComponent
      ],
      providers: [
        ArticleService,
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
    fixture = TestBed.createComponent(AdminArticleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();

    // Needed because tinymce has to be in visible component in order to be removed
    if (tinymce.activeEditor) {
      tinymce.activeEditor.remove = () => {
      };
    }
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should create a new article', inject(
    [MockBackend], (mockBackend) => {
      const mockArticle: Article = {
        title: 'My first article',
        content: 'This is the story of my life',
        creationDate: new Date()
      };

      component.article = {
        title: 'My first article',
        content: 'This is the story of my life'
      };

      mockBackend.connections.subscribe(conn => {
        conn.mockRespond(new Response(new ResponseOptions({ body: JSON.stringify(mockArticle) })));
      });

      component.submitArticle();
      expect(component.article).toEqual(mockArticle);
    }));

  it('should add a thumbnail', () => {
    const mockPicture: Picture = {
      id: 'some-random-id',
      location: 'path/to/picture.jpg',
      filename: 'picture.jpg'
    };
    component.onThumbnailUploaded(mockPicture);
    expect(component.article.thumbnailId).toEqual(mockPicture.id);
  });
});
