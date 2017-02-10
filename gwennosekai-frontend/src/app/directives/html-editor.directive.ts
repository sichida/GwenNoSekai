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
  // editor:any;

  constructor() {
  }

  ngAfterViewInit(): void {
    tinymce.init({
      selector: 'textarea', //change this to a specific class/id
      schema: 'html5',
      skin_url: 'assets/skins/lightgray',
      setup: editor => {
        // this.editor = editor;
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
