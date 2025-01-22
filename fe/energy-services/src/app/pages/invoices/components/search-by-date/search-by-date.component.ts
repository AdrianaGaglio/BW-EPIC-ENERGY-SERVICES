import { Component, Input } from '@angular/core';
import { InvoiceService } from '../../../../services/invoice.service';
import { iInvoiceresponse } from '../../../../interfaces/iinvoiceresponse';
import { iInvoiceresponseforcustomer } from '../../../../interfaces/iinvoiceresponseforcustomer';

@Component({
  selector: 'app-search-by-date',
  templateUrl: './search-by-date.component.html',
  styleUrl: './search-by-date.component.scss',
})
export class SearchByDateComponent {
  constructor(private invoiceSvc: InvoiceService) {}

  invoices: iInvoiceresponse[] | iInvoiceresponseforcustomer[] = [];

  getAllByDate(date: string) {
    this.invoiceSvc.getAllByDate(date).subscribe((res) => {
      this.invoices = res;
      console.log(this.invoices);
    });
  }
}
