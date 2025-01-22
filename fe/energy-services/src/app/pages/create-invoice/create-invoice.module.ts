import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CreateInvoiceRoutingModule } from './create-invoice-routing.module';
import { CreateInvoiceComponent } from './create-invoice.component';
import { FormsModule, NgForm, ReactiveFormsModule } from '@angular/forms';
import { NgIconsModule } from '@ng-icons/core';
import { InvoiceStatusComponent } from './components/invoice-status/invoice-status.component';

@NgModule({
  declarations: [CreateInvoiceComponent, InvoiceStatusComponent],
  imports: [
    CommonModule,
    CreateInvoiceRoutingModule,
    ReactiveFormsModule,
    NgIconsModule,
    FormsModule,
  ],
})
export class CreateInvoiceModule {}
