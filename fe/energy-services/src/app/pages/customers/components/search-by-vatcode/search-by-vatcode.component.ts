import { Component, inject } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { iCustomer } from '../../../../interfaces/icustomer';
import { CustomerService } from '../../../../services/customer.service';

@Component({
  selector: 'app-search-by-vatcode',
  templateUrl: './search-by-vatcode.component.html',
  styleUrl: './search-by-vatcode.component.scss',
})
export class SearchByVatcodeComponent {
  private customerSvc = inject(CustomerService);
  private activeModal = inject(NgbActiveModal);

  customers: iCustomer[] = [];

  getByVatCode(vatCode: string) {
    this.customerSvc.getCustomerByVatCode(vatCode).subscribe({
      next: (res) => {
        this.customers.push(res);
        this.activeModal.close(this.customers);
      },
      error: (err) => {
        console.log(err);
      },
    });
  }
}
