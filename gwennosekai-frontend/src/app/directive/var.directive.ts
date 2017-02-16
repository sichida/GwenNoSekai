import { Input, Directive } from '@angular/core';

@Directive({
  selector: '[var]',
  exportAs: 'var'
})
export class VarDirective {
  @Input() var: any;

  constructor() {
  }

  public get value(): any {
    return this.var;
  }
}
