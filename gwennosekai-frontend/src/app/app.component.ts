import {Component, OnInit} from '@angular/core';
import {PostService} from "./post.service";
import {Post} from "./shared/post.entity";

const ITEMS_PER_PAGE: number = 9;

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  posts: Array<Post>;
  pageNumber: number = 0;

  constructor(private _postService: PostService) {
  }

  ngOnInit(): void {
    this._postService.findAll(this.pageNumber, ITEMS_PER_PAGE)
      .subscribe(posts => this.posts = posts, error => console.error(error));
  }
}
