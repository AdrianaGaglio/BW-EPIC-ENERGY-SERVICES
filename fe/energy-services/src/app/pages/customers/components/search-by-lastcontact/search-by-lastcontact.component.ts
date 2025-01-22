import { Component, inject } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { iCustomer } from '../../../../interfaces/icustomer';
import { CustomerService } from '../../../../services/customer.service';

@Component({
  selector: 'app-search-by-lastcontact',
  templateUrl: './search-by-lastcontact.component.html',
  styleUrl: './search-by-lastcontact.component.scss',
})
export class SearchByLastcontactComponent {
  private customerSvc = inject(CustomerService);
  private activeModal = inject(NgbActiveModal);

  customers: iCustomer[] = [];

  getByLastContact(startDate: string, endDate: string) {
    console.log(startDate, endDate);

    this.customerSvc.getCustomersByLastContact(startDate, endDate).subscribe({
      next: (res) => {
        this.customers = res;
        this.activeModal.close(this.customers);
      },
      error: (err) => {
        console.log(err);
      },
    });
  }
}
