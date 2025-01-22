import { Component, OnInit } from '@angular/core';
import { InvoiceService } from '../../services/invoice.service';
import { iInvoiceresponse } from '../../interfaces/iinvoiceresponse';
import { iInvoiceresponseforcustomer } from '../../interfaces/iinvoiceresponseforcustomer';

@Component({
  selector: 'app-invoices',
  templateUrl: './invoices.component.html',
  styleUrl: './invoices.component.scss',
})
export class InvoicesComponent {
  constructor(private invoiceSvc: InvoiceService) {}

  invoices!: iInvoiceresponse[] | iInvoiceresponseforcustomer[];

  invoiceId: number = 0;
  invoiceNumber: number = 0;
  status: string = '';
  customerId: number = 0;
  vatCode: string = '';
  pec: string = '';
  direction: string = 'ASC';
  date: string = '';

  getAllByCustomer() {
    this.invoiceSvc
      .getAllByCustomer()
      .subscribe((res) => (this.invoices = res));
  }

  // getById(id: number) {
  //   this.invoiceSvc.getById(id).subscribe((res) => {
  //     this.invoices = [];
  //     this.invoices.push(res);
  //   });
  // }

  getByNumber(number: number) {
    this.invoiceSvc
      .getByNumber(number)
      .subscribe((res) => (this.invoices = res));
  }

  getAllByStatus(status: string) {
    this.invoiceSvc
      .getAllByStatus(status)
      .subscribe((res) => (this.invoices = res));
  }

  getAllByCustomerInfo(
    customerId?: number,
    vatCode?: string,
    pec?: string,
    direction: string = 'ASC'
  ) {
    this.invoiceSvc
      .getAllByCustomerInfo(customerId, vatCode, pec, direction)
      .subscribe((res) => (this.invoices = res));
  }

  getAllByDate(date: string) {
    this.invoiceSvc
      .getAllByDate(date)
      .subscribe((res) => (this.invoices = res));
  }

  getAllByYear(year: number) {
    this.invoiceSvc
      .getAllByYear(year)
      .subscribe((res) => (this.invoices = res));
  }

  getAllByAmountRange(min: number, max: number) {
    this.invoiceSvc
      .getAllByAmountRange(min, max)
      .subscribe((res) => (this.invoices = res));
  }
}
