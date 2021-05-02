import { Directive, Input, TemplateRef, ViewContainerRef } from '@angular/core';

@Directive({
  selector: '[appExceto]'
})
export class ExcetoDirective {

  constructor(
    private templateRef: TemplateRef <any>,
    private vcRef: ViewContainerRef
  ) {}

  @Input() set appExceto(condition: boolean) {
    if (!condition) {
      this.vcRef.createEmbeddedView(this.templateRef);
    } else {
      this.vcRef.clear();
    }
  }

}
