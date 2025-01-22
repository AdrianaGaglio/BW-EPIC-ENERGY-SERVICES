import { iCustomer } from '../../../../interfaces/icustomer';
import { CustomerService } from '../../../../services/customer.service';
import { Component, inject } from '@angular/core';

@Component({
  selector: 'app-logged-customers',
  templateUrl: './logged-customers.component.html',
  styleUrl: './logged-customers.component.scss',
})
export class LoggedCustomersComponent {
  private customerSvc = inject(CustomerService);

  customer!: iCustomer;

  ngOnInit() {
    this.getCustomerByLoggedCustomer();
  }
  getCustomerByLoggedCustomer() {
    this.customerSvc.getCustomerByLoggedCustomer().subscribe((data) => {
      this.customer = data;
    });
  }
}
