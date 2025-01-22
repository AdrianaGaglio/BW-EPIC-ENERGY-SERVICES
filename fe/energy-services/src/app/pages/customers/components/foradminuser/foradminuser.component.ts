import { Component, inject } from '@angular/core';
import { CustomerService } from '../../../../services/customer.service';
import { iCustomer } from '../../../../interfaces/icustomer';

@Component({
  selector: 'app-foradminuser',
  templateUrl: './foradminuser.component.html',
  styleUrl: './foradminuser.component.scss',
})
export class ForadminuserComponent {
  private customerSvc = inject(CustomerService);
  searchBy: string = 'all';
  customers: iCustomer[] = [];
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
      this.customers.push(data);
    });
  }

  getCustomerByUsername(username: string) {
    this.customerSvc.getCustomerByUsername(username).subscribe((data) => {
      this.customers.push(data);
    });
  }

  openSearchByCustomer() {
    // this.modalService.open(SearchByCustomerComponent, { size: 'xl' });
  }

  openSearchByStatus() {
    // this.modalService.open(SearchByStatusComponent, { size: 'xl' });
  }

  openSearchByNumber() {
    // this.modalService.open(SearchByNumberComponent, { size: 'xl' });
  }

  openSearchByCustomerInfo() {
    // this.modalService.open(SearchByCustomerInfoComponent, { size: 'xl' });
  }

  openSearchByDate() {
    // this.modalService.open(SearchByDateComponent, { size: 'xl' });
  }

  openSearchByYear() {
    // this.modalService.open(SearchByYearComponent, { size: 'xl' });
  }

  openSearchByAmount() {
    // this.modalService.open(SearchByAmountComponent, { size: 'xl' });
  }
}
