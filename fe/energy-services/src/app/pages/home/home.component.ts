import { Component } from '@angular/core';
import { InvoiceService } from '../../services/invoice.service';
import { iTotalresponse } from '../../interfaces/itotalresponse';
import { iInvoiceresponse } from '../../interfaces/iinvoiceresponse';
import { iInvoicepageresponse } from '../../interfaces/iinvoicepageresponse';
import { CustomerService } from '../../services/customer.service';
import { iTotalcustomersresponse } from '../../interfaces/itotalcustomersresponse';
import { DecodeTokenService } from '../../services/decode-token.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss',
})
export class HomeComponent {
  constructor(
    private invoiceSvc: InvoiceService,
    private customerSvc: CustomerService,
    private decodeToken: DecodeTokenService
  ) {}

  totalAmount!: iTotalresponse;
  lastYearAmount!: iTotalresponse;
  totalCustomers!: iTotalcustomersresponse;

  waitingPayments: iInvoiceresponse[] = [];

  limit: number = 3;

  latest: iInvoiceresponse[] = [];

  ngOnInit() {
    let roles: string[] = this.decodeToken.userRoles$.getValue();
    if (!roles.includes('CUSTOMER')) {
      this.invoiceSvc.getTotal(new Date().getFullYear()).subscribe((res) => {
        if (res && res.total !== undefined) {
          this.totalAmount = {
            ...res,
            total: parseFloat(res.total.toFixed(2))
          };
        }
      });

      this.invoiceSvc
        .getTotal(new Date().getFullYear() - 1)
        .subscribe((res) => {
          this.lastYearAmount = res;
        });

      this.invoiceSvc.getWaitingPayment().subscribe((res) => {
        this.waitingPayments = res;
      });

      this.invoiceSvc.getLatest(this.limit).subscribe((res) => {
        this.latest = res.content;
      });

      this.customerSvc.getTotal().subscribe((res) => {
        this.totalCustomers = res;
      });
    }
  }

  changeLimit() {
    this.invoiceSvc.getLatest(this.limit).subscribe((res) => {
      this.latest = res.content;
    });
  }
}
