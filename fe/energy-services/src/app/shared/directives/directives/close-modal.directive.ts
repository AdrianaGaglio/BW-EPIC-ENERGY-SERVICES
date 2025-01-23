import { Directive, ElementRef, HostListener } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Directive({
  selector: '[appCloseModal]',
})
export class CloseModalDirective {
  constructor(private ref: ElementRef, private modalService: NgbModal) {}

  ngAfterViewInit() {
    console.log(this.ref.nativeElement);
  }

  @HostListener('click', ['$event']) onClick(event: MouseEvent) {
    console.log(event.target);
  }
}
