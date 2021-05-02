import { Directive, ElementRef, HostListener, Renderer2 } from '@angular/core';

@Directive({
  selector: '[appFade]'
})
export class FadeDirective {

  constructor(private el: ElementRef, private renderer: Renderer2) {
    el.nativeElement.style.opacity = '.6';
    el.nativeElement.style.transition = '.4s opacity';
    //el.nativeElement.style.backgroundColor = 'yellow';
  }

  @HostListener('mouseover') mouseover(): void {
    this.renderer.setStyle(this.el.nativeElement, 'opacity', '1');
  }

  @HostListener('mouseout') mouseout(): void {
    this.renderer.setStyle(this.el.nativeElement, 'opacity', '.6');
  }

}
