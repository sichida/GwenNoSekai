import { Component, OnInit, EventEmitter, Output } from '@angular/core';
import { Picture } from '../../shared/picture.entity';
import { PictureService } from '../../service/picture.service';

@Component({
  selector: 'app-admin-thumbnail',
  templateUrl: './admin-thumbnail.component.html',
  styleUrls: ['./admin-thumbnail.component.scss']
})
export class AdminThumbnailComponent implements OnInit {
  @Output()
  pictureUploaded: EventEmitter<Picture> = new EventEmitter<Picture>();
  picture: Picture;

  constructor(private pictureService: PictureService) {
  }

  ngOnInit() {
  }

  upload(file: File) {
    this.pictureService.uploadThumbnail(file)
      .subscribe(picture => {
        console.log(picture);
        this.picture = picture;
        this.pictureUploaded.emit(picture);
      });
  }

}
