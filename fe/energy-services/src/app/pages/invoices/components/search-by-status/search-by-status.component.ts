import { RegisterComponent } from './../../../../auth/register/register.component';
import { Component, Input } from '@angular/core';
import { iInvoiceresponseforcustomer } from '../../../../interfaces/iinvoiceresponseforcustomer';
import { iInvoiceresponse } from '../../../../interfaces/iinvoiceresponse';
import { InvoiceService } from '../../../../services/invoice.service';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-search-by-status',
  templateUrl: './search-by-status.component.html',
  styleUrl: './search-by-status.component.scss',
})
export class SearchByStatusComponent {
  constructor(
    private invoiceSvc: InvoiceService,
    private activeModal: NgbActiveModal
  ) {}

  invoices: iInvoiceresponse[] | iInvoiceresponseforcustomer[] = [];

  getAllByStatus(status: string) {
    this.invoiceSvc.getAllByStatus(status).subscribe((res) => {
      this.invoices = res;
      this.activeModal.close(this.invoices);
    });
  }
}
