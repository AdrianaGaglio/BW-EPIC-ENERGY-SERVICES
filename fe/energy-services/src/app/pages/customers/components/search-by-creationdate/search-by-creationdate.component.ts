import { Component, inject } from '@angular/core';
import { iCustomer } from '../../../../interfaces/icustomer';
import { CustomerService } from '../../../../services/customer.service';

@Component({
  selector: 'app-search-by-creationdate',
  templateUrl: './search-by-creationdate.component.html',
  styleUrl: './search-by-creationdate.component.scss',
})
export class SearchByCreationdateComponent {
  private customerSvc = inject(CustomerService);

  customer: iCustomer[] = [];

  getByCreationDate(startDate: string, endDate: string) {
    console.log(startDate, endDate);

    this.customerSvc.getCustomersByCreationDate(startDate, endDate).subscribe({
      next: (res) => {
        this.customer = res;
        console.log(this.customer);
      },
      error: (err) => {
        console.log(err);
      },
    });
  }
}
