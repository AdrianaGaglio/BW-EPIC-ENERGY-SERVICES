import { Component, inject, Input } from '@angular/core';
import { NgbActiveModal, NgbOffcanvas } from '@ng-bootstrap/ng-bootstrap';
import { InvoiceService } from '../../../../services/invoice.service';
import { iInvoiceresponse } from '../../../../interfaces/iinvoiceresponse';
import { iInvoiceresponseforcustomer } from '../../../../interfaces/iinvoiceresponseforcustomer';

@Component({
  selector: 'app-search-by-number',
  templateUrl: './search-by-number.component.html',
  styleUrl: './search-by-number.component.scss',
})
export class SearchByNumberComponent {
  constructor(
    private invoiceSvc: InvoiceService,
    private activeModal: NgbActiveModal
  ) {}

  invoices: iInvoiceresponse[] | iInvoiceresponseforcustomer[] = [];

  getByNumber(number: number) {
    this.invoiceSvc.getByNumber(number).subscribe((res) => {
      this.invoices.push(res);
      this.activeModal.close(this.invoices);
    });
  }
}
