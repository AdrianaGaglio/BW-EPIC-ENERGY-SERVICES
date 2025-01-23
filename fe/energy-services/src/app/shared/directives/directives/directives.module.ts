import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CloseModalDirective } from './close-modal.directive';

@NgModule({
  declarations: [CloseModalDirective],
  imports: [CommonModule],
  exports: [CloseModalDirective],
})
export class DirectivesModule {}
