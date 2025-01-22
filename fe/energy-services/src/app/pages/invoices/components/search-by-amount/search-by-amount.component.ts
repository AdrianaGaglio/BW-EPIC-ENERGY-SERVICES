import { Component, Input } from '@angular/core';
import { InvoiceService } from '../../../../services/invoice.service';
import { iInvoiceresponse } from '../../../../interfaces/iinvoiceresponse';
import { iInvoiceresponseforcustomer } from '../../../../interfaces/iinvoiceresponseforcustomer';

@Component({
  selector: 'app-search-by-amount',
  templateUrl: './search-by-amount.component.html',
  styleUrl: './search-by-amount.component.scss',
})
export class SearchByAmountComponent {
  constructor(private invoiceSvc: InvoiceService) {}

  invoices: iInvoiceresponse[] | iInvoiceresponseforcustomer[] = [];

  getAllByAmountRange(min: number, max: number) {
    this.invoiceSvc.getAllByAmountRange(min, max).subscribe((res) => {
      this.invoices = res;
      console.log(this.invoices);
    });
  }
}
