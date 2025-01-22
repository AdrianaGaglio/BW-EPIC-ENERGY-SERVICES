import { Component, Input } from '@angular/core';
import { InvoiceService } from '../../../../services/invoice.service';
import { iInvoiceresponse } from '../../../../interfaces/iinvoiceresponse';
import { iInvoiceresponseforcustomer } from '../../../../interfaces/iinvoiceresponseforcustomer';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-search-by-amount',
  templateUrl: './search-by-amount.component.html',
  styleUrl: './search-by-amount.component.scss',
})
export class SearchByAmountComponent {
  constructor(
    private invoiceSvc: InvoiceService,
    private activeModal: NgbActiveModal
  ) {}

  min: string = '';
  max: string = '';

  invoices: iInvoiceresponse[] | iInvoiceresponseforcustomer[] = [];

  getAllByAmountRange(min: number, max: number) {
    if (min == 0 || max == 0) {
      alert('Amount values are required');
    } else {
      this.invoiceSvc.getAllByAmountRange(min, max).subscribe((res) => {
        this.invoices = res;
        this.activeModal.close(this.invoices);
      });
    }
  }
}
