import { iInvoiceresponseforcustomer } from './../../../../interfaces/iinvoiceresponseforcustomer';
import { Component, Input } from '@angular/core';
import { iInvoiceresponse } from '../../../../interfaces/iinvoiceresponse';
import { InvoiceService } from '../../../../services/invoice.service';

@Component({
  selector: 'app-search-by-year',
  templateUrl: './search-by-year.component.html',
  styleUrl: './search-by-year.component.scss',
})
export class SearchByYearComponent {
  constructor(private invoiceSvc: InvoiceService) {}

  invoices: iInvoiceresponse[] | iInvoiceresponseforcustomer[] = [];

  getAllByYear(year: number) {
    this.invoiceSvc.getAllByYear(year).subscribe((res) => {
      this.invoices = res;
      console.log(this.invoices);
    });
  }
}
