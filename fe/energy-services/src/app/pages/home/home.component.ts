import { Component } from '@angular/core';
import { InvoiceService } from '../../services/invoice.service';
import { iTotalresponse } from '../../interfaces/itotalresponse';
import { iInvoiceresponse } from '../../interfaces/iinvoiceresponse';
import { iInvoicepageresponse } from '../../interfaces/iinvoicepageresponse';
import { CustomerService } from '../../services/customer.service';
import { iTotalcustomersresponse } from '../../interfaces/itotalcustomersresponse';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss',
})
export class HomeComponent {
  constructor(
    private invoiceSvc: InvoiceService,
    private customerSvc: CustomerService
  ) {}

  totalAmount!: iTotalresponse;
  lastYearAmount!: iTotalresponse;
  totalCustomers!: iTotalcustomersresponse;

  waitingPayments: iInvoiceresponse[] = [];

  limit: number = 3;

  latest: iInvoiceresponse[] = [];

  ngOnInit() {
    this.invoiceSvc.getTotal(new Date().getFullYear()).subscribe((res) => {
      this.totalAmount = res;
      console.log(this.totalAmount);
    });

    this.invoiceSvc.getTotal(new Date().getFullYear() - 1).subscribe((res) => {
      this.lastYearAmount = res;
      console.log(this.lastYearAmount);
    });

    this.invoiceSvc.getWaitingPayment().subscribe((res) => {
      this.waitingPayments = res;
      console.log(this.waitingPayments);
    });

    this.invoiceSvc.getLatest(this.limit).subscribe((res) => {
      this.latest = res.content;
      console.log(this.latest);
    });

    this.customerSvc.getTotal().subscribe((res) => {
      this.totalCustomers = res;
      console.log(res);
    });
  }

  changeLimit() {
    this.invoiceSvc.getLatest(this.limit).subscribe((res) => {
      this.latest = res.content;
    });
  }
}
