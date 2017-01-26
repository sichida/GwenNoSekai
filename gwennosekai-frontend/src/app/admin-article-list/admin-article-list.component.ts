import { Component, OnInit } from '@angular/core';
import {PostService} from "../post.service";
import {Post} from "../shared/post.entity";

@Component({
  selector: 'app-admin-article-list',
  templateUrl: './admin-article-list.component.html',
  styleUrls: ['./admin-article-list.component.scss']
})
export class AdminArticleListComponent implements OnInit {
  private posts:Array<Post>;

  constructor(private _postService:PostService) { }

  ngOnInit() {
     this._postService.findAll(0, 9).subscribe(posts => this.posts = posts);
  }

}
