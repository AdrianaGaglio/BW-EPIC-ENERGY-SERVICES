import { Component, Input } from '@angular/core';
import { InvoiceService } from '../../../../services/invoice.service';
import { iInvoiceresponse } from '../../../../interfaces/iinvoiceresponse';
import { iInvoiceresponseforcustomer } from '../../../../interfaces/iinvoiceresponseforcustomer';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-search-by-customer-info',
  templateUrl: './search-by-customer-info.component.html',
  styleUrl: './search-by-customer-info.component.scss',
})
export class SearchByCustomerInfoComponent {
  constructor(
    private invoiceSvc: InvoiceService,
    private activeModal: NgbActiveModal
  ) {}

  invoices: iInvoiceresponse[] | iInvoiceresponseforcustomer[] = [];

  customerId: string = '';
  vatCode: string = '';
  pec: string = '';
  direction: string = 'ASC';

  getAllByCustomerInfo() {
    if (this.customerId != '') {
      this.invoiceSvc
        .getAllByCustomerInfo(+this.customerId, this.direction)
        .subscribe((res) => {
          this.invoices = res;
          this.activeModal.close(this.invoices);
        });
    } else if (this.vatCode != '') {
      this.invoiceSvc
        .getAllByCustomerInfo(this.vatCode, this.direction)
        .subscribe((res) => {
          this.invoices = res;
          this.activeModal.close(this.invoices);
        });
    } else if (this.pec != '') {
      this.invoiceSvc
        .getAllByCustomerInfo(this.pec, this.direction)
        .subscribe((res) => {
          this.invoices = res;
          this.activeModal.close(this.invoices);
        });
    }
  }
}
