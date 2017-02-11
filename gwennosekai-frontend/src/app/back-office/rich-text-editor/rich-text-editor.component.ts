import {
  Input,
  Output,
  EventEmitter,
  Component,
  OnInit,
  AfterViewInit,
  OnDestroy,
  ElementRef,
  ViewChild
} from '@angular/core';
import 'tinymce';
import { PictureService } from '../../service/picture.service';

@Component({
  selector: 'app-rich-text-editor',
  templateUrl: './rich-text-editor.component.html',
  styleUrls: ['./rich-text-editor.component.scss']
})
export class RichTextEditorComponent implements OnInit, AfterViewInit, OnDestroy {
  @Input()
  content: string;
  @Output()
  contentChange: EventEmitter<string> = new EventEmitter<string>();
  @ViewChild('upload') el: ElementRef;

  constructor(private pictureService: PictureService) {
  }

  ngOnInit(): void {
  }

  ngAfterViewInit(): void {
    tinymce.init({
      selector: '.tinymce', //change this to a specific class/id
      schema: 'html5',
      skin_url: 'assets/skins/lightgray',
      height: 500,
      theme: 'modern',
      plugins: [
        'advlist autolink lists link image charmap print preview hr anchor pagebreak',
        'searchreplace wordcount visualblocks visualchars code fullscreen',
        'insertdatetime media nonbreaking save table contextmenu directionality',
        'emoticons template paste textcolor colorpicker textpattern imagetools toc'
      ],
      toolbar1: 'undo redo | insert | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image',
      toolbar2: 'print preview | forecolor backcolor emoticons',
      image_advtab: true,
      file_picker_callback: (callback, value, meta) => {
        this.el.nativeElement.onchange = (event) => {
          const file: File = this.el.nativeElement.files[0];
          this.pictureService.upload(this.el.nativeElement.files[0])
            .subscribe(picture => callback(picture.location, { alt: '' }));
        };
        if (meta.filetype == 'image') {
          this.el.nativeElement.click();
        }
      },
      setup: editor => {
        editor.on('keyup', () => {
          const content = editor.getContent();
          this.contentChange.emit(content);
        });
      },
      images_upload_url: '/api/v1/picture'
    });
    if (this.content) {
      tinymce.activeEditor.setContent(this.content);
    }
  }

  ngOnDestroy() {
    tinymce.activeEditor.remove();
  }
}
