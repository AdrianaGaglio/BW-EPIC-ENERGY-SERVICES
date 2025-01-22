import { Component } from '@angular/core';
import { CustomerService } from '../../services/customer.service';
import { DecodeTokenService } from '../../services/decode-token.service';
import { iCustomer } from '../../interfaces/icustomer';
import { Icustomerpageresponse } from '../../interfaces/icustomerpageresponse';

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrl: './customers.component.scss',
})
export class CustomersComponent {
  constructor(
    private customerSvc: CustomerService,
    private decodeToken: DecodeTokenService
  ) {
    this.roles = this.decodeToken.userRoles$.getValue();
  }
  roles!: string[];
  customer!: iCustomer;
  customers!: iCustomer[];

  ngOnInit(): void {}

  getAllCustomers(numberPage: number, size: number, type?: string[]) {
    this.customerSvc
      .getAllCustomers(numberPage, size, type)
      .subscribe((data) => {
        this.customers = data.content;
      });
  }

  getCustomersByCreationDate(startDate: Date, endDate: Date) {
    this.customerSvc
      .getCustomersByCreationDate(startDate, endDate)
      .subscribe((data) => {
        this.customers = data;
      });
  }

  getCustomersByLastContact(startDate: Date, endDate: Date) {
    this.customerSvc
      .getCustomersByLastContact(startDate, endDate)
      .subscribe((data) => {
        this.customers = data;
      });
  }

  getCustomersByDenominationsContain(searchTerm: string) {
    this.customerSvc
      .getCustomersByDenominationsContain(searchTerm)
      .subscribe((data) => {
        this.customers = data;
      });
  }

  getCustomersByYearlyTurnover(min: number, max: number) {
    this.customerSvc
      .getCustomersByYearlyTurnover(min, max)
      .subscribe((data) => {
        this.customers = data;
      });
  }

  getCustomerByVatCode(vatCode: string) {
    this.customerSvc.getCustomerByVatCode(vatCode).subscribe((data) => {
      this.customer = data;
    });
  }

  getCustomerByUsername(username: string) {
    this.customerSvc.getCustomerByUsername(username).subscribe((data) => {
      this.customer = data;
    });
  }

  getCustomerByLoggedCustomer() {
    this.customerSvc.getCustomerByLoggedCustomer().subscribe((data) => {
      this.customer = data;
    });
  }
}
