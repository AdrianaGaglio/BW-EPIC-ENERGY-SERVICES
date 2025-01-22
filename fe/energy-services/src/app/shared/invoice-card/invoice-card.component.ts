import { Component, Input } from '@angular/core';
import { iInvoiceresponse } from '../../interfaces/iinvoiceresponse';

@Component({
  selector: 'app-invoice-card',
  templateUrl: './invoice-card.component.html',
  styleUrl: './invoice-card.component.scss',
})
export class InvoiceCardComponent {
  @Input() invoice!: iInvoiceresponse;
}
