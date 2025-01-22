import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { InvoiceCardComponent } from './invoice-card.component';
import { UpdateInvoiceComponent } from './update-invoice/update-invoice.component';
import { FormsModule } from '@angular/forms';
import { CustomerInfoComponent } from './customer-info/customer-info.component';

@NgModule({
  declarations: [InvoiceCardComponent, UpdateInvoiceComponent, CustomerInfoComponent],
  imports: [CommonModule, FormsModule],
  exports: [InvoiceCardComponent],
})
export class InvoiceCardModule {}
