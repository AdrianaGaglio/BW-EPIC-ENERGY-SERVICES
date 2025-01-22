import { Component, inject, input, OnInit } from '@angular/core';
import { InvoiceService } from '../../services/invoice.service';
import { iInvoiceresponse } from '../../interfaces/iinvoiceresponse';
import { iInvoiceresponseforcustomer } from '../../interfaces/iinvoiceresponseforcustomer';
import { NgbModal, NgbOffcanvas } from '@ng-bootstrap/ng-bootstrap';
import { SearchByNumberComponent } from './components/search-by-number/search-by-number.component';
import { SearchByCustomerComponent } from './components/search-by-customer/search-by-customer.component';
import { SearchByStatusComponent } from './components/search-by-status/search-by-status.component';
import { SearchByCustomerInfoComponent } from './components/search-by-customer-info/search-by-customer-info.component';
import { SearchByDateComponent } from './components/search-by-date/search-by-date.component';
import { SearchByYearComponent } from './components/search-by-year/search-by-year.component';
import { SearchByAmountComponent } from './components/search-by-amount/search-by-amount.component';

@Component({
  selector: 'app-invoices',
  templateUrl: './invoices.component.html',
  styleUrl: './invoices.component.scss',
})
export class InvoicesComponent {
  constructor(private invoiceSvc: InvoiceService) {}
  private modalService = inject(NgbModal);

  invoices!: iInvoiceresponse[];

  customerInvoices!: iInvoiceresponseforcustomer[];

  invoiceId: number = 0;
  invoiceNumber: number = 0;
  status: string = '';
  customerId: number = 0;
  vatCode: string = '';
  pec: string = '';
  direction: string = 'ASC';
  date: string = '';

  page!: number;
  pages!: number[];

  searchBy: string = 'all';

  ngOnInit() {
    this.invoiceSvc.getAllPaged(0, 10, 'number,desc').subscribe((res) => {
      this.invoices = res.content;
      this.pages = Array.from({ length: res.totalPages }, (_, i) => i + 1);
    });
  }

  getAll() {
    this.invoiceSvc.getAllPaged(0, 10, 'number,desc').subscribe((res) => {
      this.invoices = res.content;
    });
  }

  changePage(num: number) {
    this.invoiceSvc.getAllPaged(num, 10, 'number,desc').subscribe((res) => {
      this.invoices = res.content;
    });
  }

  getAllByCustomer() {
    this.invoiceSvc
      .getAllByCustomer()
      .subscribe((res) => (this.customerInvoices = res));
  }

  openSearchByCustomer() {
    this.modalService.open(SearchByCustomerComponent, { size: 'xl' });
  }

  openSearchByStatus() {
    this.modalService.open(SearchByStatusComponent, { size: 'xl' });
  }

  openSearchByNumber() {
    const modalRef = this.modalService.open(SearchByNumberComponent, {
      size: 'xl',
    });

    modalRef.result.then((res) => {
      this.invoices = res;
    });
  }

  openSearchByCustomerInfo() {
    this.modalService.open(SearchByCustomerInfoComponent, { size: 'xl' });
  }

  openSearchByDate() {
    this.modalService.open(SearchByDateComponent, { size: 'xl' });
  }

  openSearchByYear() {
    this.modalService.open(SearchByYearComponent, { size: 'xl' });
  }

  openSearchByAmount() {
    this.modalService.open(SearchByAmountComponent, { size: 'xl' });
  }
}
