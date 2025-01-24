import { Component, inject, input, OnInit } from '@angular/core';
import { InvoiceService } from '../../services/invoice.service';
import { iInvoiceresponse } from '../../interfaces/iinvoiceresponse';
import { iInvoiceresponseforcustomer } from '../../interfaces/iinvoiceresponseforcustomer';
import { NgbModal, NgbAlert } from '@ng-bootstrap/ng-bootstrap';
import { SearchByNumberComponent } from './components/search-by-number/search-by-number.component';
import { SearchByCustomerComponent } from './components/search-by-customer/search-by-customer.component';
import { SearchByStatusComponent } from './components/search-by-status/search-by-status.component';
import { SearchByCustomerInfoComponent } from './components/search-by-customer-info/search-by-customer-info.component';
import { SearchByDateComponent } from './components/search-by-date/search-by-date.component';
import { SearchByYearComponent } from './components/search-by-year/search-by-year.component';
import { SearchByAmountComponent } from './components/search-by-amount/search-by-amount.component';
import { DecodeTokenService } from '../../services/decode-token.service';
import { CustomerService } from '../../services/customer.service';
import { AuthsrvService } from '../../auth/authsrv.service';

@Component({
  selector: 'app-invoices',
  templateUrl: './invoices.component.html',
  styleUrl: './invoices.component.scss',
})
export class InvoicesComponent {
  constructor(
    private invoiceSvc: InvoiceService,
    private authSvc: AuthsrvService,
    private decodeToken: DecodeTokenService,
    private customerSvc: CustomerService
  ) {
    if (
      this.authSvc.userAuthSubject$ &&
      !this.decodeToken.userRoles$.getValue().includes('CUSTOMER')
    ) {
      this.customerSvc.getAll().subscribe();
    }
  }
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

  isPaged: boolean = true;
  page!: number;
  pages!: number[];

  message: string = '';

  isLoading: boolean = true;

  customerFromlocalStorage: string | null = null;
  searchBy: string = 'all';

  roles: string[] = [];

  ngOnInit() {
    this.roles = this.decodeToken.userRoles$.getValue();

    this.customerFromlocalStorage =
      sessionStorage.getItem('customerToInvoices');

    if (this.customerFromlocalStorage) {
      this.openSearchByCustomerInfo();
      this.searchBy = 'byCustomerInfo';
    } else {
      this.getAll();
    }
  }

  getAll() {
    if (!this.roles.includes('CUSTOMER')) {
      this.invoiceSvc.getAllPaged(0, 10, 'number,desc').subscribe((res) => {
        this.isLoading = false;
        this.isPaged = true;
        this.pages = Array.from({ length: res.totalPages }, (_, i) => i + 1);
        this.invoices = res.content;
        if (res.content.length == 0) {
          this.message = 'No invoices found';
        }
      });
    } else {
      this.getAllByCustomer();
    }
  }

  changePage(num: number) {
    this.invoiceSvc.getAllPaged(num, 10, 'number,desc').subscribe((res) => {
      this.invoices = res.content;
    });
  }

  getAllByCustomer() {
    this.invoiceSvc.getAllByCustomer().subscribe((res) => {
      this.isLoading = false;
      this.customerInvoices = res;
      if (res.length == 0) {
        this.message = 'No invoices found';
      }
    });
  }

  openSearchByCustomer() {
    this.modalService.open(SearchByCustomerComponent, {
      size: 'xl',
    });
  }

  openSearchByStatus() {
    const modalRef = this.modalService.open(SearchByStatusComponent, {
      size: 'xl',
    });

    if (this.message) this.clearMessage();

    modalRef.result
      .then((res) => {
        this.isPaged = false;
        this.searchBy = '';
        if (this.roles.includes('CUSTOMER')) {
          this.customerInvoices = res;
        } else {
          this.invoices = res;
        }
        if (res.length == 0) {
          this.message = 'No invoices found';
        }
      })
      .catch((reason) => {
        this.modalService.dismissAll();
        this.searchBy = '';
      });
  }

  openSearchByNumber() {
    const modalRef = this.modalService.open(SearchByNumberComponent, {
      size: 'xl',
    });

    if (this.message) this.clearMessage();

    modalRef.result
      .then((res) => {
        this.isPaged = false;
        this.searchBy = '';
        if (this.roles.includes('CUSTOMER')) {
          this.customerInvoices = res;
        } else {
          this.invoices = res;
        }
        if (res.length == 0) {
          this.message = 'No invoices found';
        }
      })
      .catch((reason) => {
        this.modalService.dismissAll();
        this.searchBy = '';
      });
  }

  openSearchByCustomerInfo() {
    const modalRef = this.modalService.open(SearchByCustomerInfoComponent, {
      size: 'xl',
    });

    if (this.message) this.clearMessage();

    modalRef.result
      .then((res) => {
        this.isPaged = false;
        this.searchBy = '';
        this.invoices = res;
        if (res.length == 0) {
          this.message = 'No invoices found';
        }
      })
      .catch((reason) => {
        this.modalService.dismissAll();
        this.searchBy = '';
      });
  }

  openSearchByDate() {
    const modalRef = this.modalService.open(SearchByDateComponent, {
      size: 'xl',
    });

    if (this.message) this.clearMessage();

    modalRef.result
      .then((res) => {
        this.isPaged = false;
        this.searchBy = '';
        if (this.roles.includes('CUSTOMER')) {
          this.customerInvoices = res;
        } else {
          this.invoices = res;
        }
        if (res.length == 0) {
          this.message = 'No invoices found';
        }
      })
      .catch((reason) => {
        this.modalService.dismissAll();
        this.searchBy = '';
      });
  }

  openSearchByYear() {
    const modalRef = this.modalService.open(SearchByYearComponent, {
      size: 'xl',
    });

    if (this.message) this.clearMessage();

    modalRef.result
      .then((res) => {
        this.isPaged = false;
        this.searchBy = '';
        if (this.roles.includes('CUSTOMER')) {
          this.customerInvoices = res;
        } else {
          this.invoices = res;
        }
        if (res.length == 0) {
          this.message = 'No invoices found';
        }
      })
      .catch((reason) => {
        this.modalService.dismissAll();
        this.searchBy = '';
      });
  }

  openSearchByAmount() {
    const modalRef = this.modalService.open(SearchByAmountComponent, {
      size: 'xl',
    });

    if (this.message) this.clearMessage();

    modalRef.result
      .then((res) => {
        this.isPaged = false;
        this.searchBy = '';
        if (this.roles.includes('CUSTOMER')) {
          this.customerInvoices = res;
        } else {
          this.invoices = res;
        }
      })
      .catch((reason) => {
        this.modalService.dismissAll();
        this.searchBy = '';
      });
  }

  clearMessage() {
    this.message = '';
    this.searchBy = 'all';
    this.getAll();
    this.isLoading = true;
  }
}
