import {Component, OnInit} from "@angular/core";
import {Post} from "../shared/post.entity";
import {PostService} from "../post.service";

const ITEMS_PER_PAGE: number = 9;

@Component({
  selector: 'app-front-office',
  templateUrl: './front-office.component.html',
  styleUrls: ['./front-office.component.scss']
})
export class FrontOfficeComponent implements OnInit {

  posts: Array<Post>;
  pageNumber: number = 0;

  constructor(private _postService: PostService) {
  }

  ngOnInit(): void {
    this._postService.findAll(this.pageNumber, ITEMS_PER_PAGE)
      .subscribe(posts => this.posts = posts, error => console.error(error));
  }
}
