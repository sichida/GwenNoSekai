import { Component, OnInit, Input } from '@angular/core';
import {Post} from "../../shared/post.entity";
@Component({
  selector: 'app-post-list',
  templateUrl: './post-list.component.html',
  styleUrls: ['./post-list.component.scss']
})
export class PostListComponent implements OnInit {
  @Input() posts:Array<Post>;

  constructor() { }

  ngOnInit() {
  }

}
