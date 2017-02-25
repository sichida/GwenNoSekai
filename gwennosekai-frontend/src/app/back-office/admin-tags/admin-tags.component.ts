import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Observable } from 'rxjs';
import { Response } from '@angular/http';
import { TagService } from '../../service/tag.service';

@Component({
  selector: 'app-admin-tags',
  templateUrl: './admin-tags.component.html',
  styleUrls: ['./admin-tags.component.scss']
})
export class AdminTagsComponent implements OnInit {
  @Input()
  public tags: Array<string>;
  @Output()
  public tagsChange:EventEmitter<Array<string>> = new EventEmitter<Array<string>>();

  constructor(private tagService: TagService) {
  }

  public ngOnInit() {
    if (!this.tags) {
      this.tags = new Array<string>();
    }
  }

  public requestAutocompleteItems = (query: string): Observable<Response> => {
    return this.tagService.query(query);
  };

  public onTagAdded(tag: any) {
    this.tagsChange.emit(this.tags);
  }
}
