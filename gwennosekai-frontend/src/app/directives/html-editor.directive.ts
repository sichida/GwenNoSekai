import { Input, Output, EventEmitter, Directive, AfterViewInit, OnDestroy } from '@angular/core';
import 'tinymce';

@Directive({
  selector: '[htmlEditor]'
})
export class HtmlEditorDirective implements AfterViewInit, OnDestroy {
  @Input()
  ngModel: string;
  @Output()
  ngModelChange: EventEmitter<string> = new EventEmitter<string>();

  constructor() {
  }

  ngAfterViewInit(): void {
    tinymce.init({
      selector: 'textarea', //change this to a specific class/id
      schema: 'html5',
      skin_url: 'assets/skins/lightgray',
      height: 500,
      theme: 'modern',
      plugins: [
        'advlist autolink lists link image charmap print preview hr anchor pagebreak',
        'searchreplace wordcount visualblocks visualchars code fullscreen',
        'insertdatetime media nonbreaking save table contextmenu directionality',
        'emoticons template paste textcolor colorpicker textpattern imagetools codesample toc'
      ],
      toolbar1: 'undo redo | insert | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image',
      toolbar2: 'print preview media | forecolor backcolor emoticons | codesample',
      image_advtab: true,
      setup: editor => {
        editor.on('keyup', () => {
          const content = editor.getContent();
          this.ngModelChange.emit(content);
        });
      }
    });
    tinymce.activeEditor.setContent(this.ngModel);
  }

  ngOnDestroy() {
    tinymce.activeEditor.remove();
  }
}
