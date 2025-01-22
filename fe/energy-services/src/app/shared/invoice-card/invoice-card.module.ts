import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { InvoiceCardComponent } from './invoice-card.component';

@NgModule({
  declarations: [InvoiceCardComponent],
  imports: [CommonModule],
  exports: [InvoiceCardComponent],
})
export class InvoiceCardModule {}
