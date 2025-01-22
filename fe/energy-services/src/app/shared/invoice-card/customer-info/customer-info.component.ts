import { Component, Input } from '@angular/core';
import { iCustomerresponseforinvoice } from '../../../interfaces/icustomerresponseforinvoice';
import { iInvoiceresponse } from '../../../interfaces/iinvoiceresponse';

@Component({
  selector: 'app-customer-info',
  templateUrl: './customer-info.component.html',
  styleUrl: './customer-info.component.scss',
})
export class CustomerInfoComponent {
  @Input() invoice!: Partial<iInvoiceresponse>;
}
