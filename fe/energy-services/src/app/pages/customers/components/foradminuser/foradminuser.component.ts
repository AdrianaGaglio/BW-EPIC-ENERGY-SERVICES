import { Component, inject } from '@angular/core';
import { CustomerService } from '../../../../services/customer.service';
import { iCustomer } from '../../../../interfaces/icustomer';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { SearchByCreationdateComponent } from '../search-by-creationdate/search-by-creationdate.component';
import { SearchByLastcontactComponent } from '../search-by-lastcontact/search-by-lastcontact.component';
import { SearchByDenominationComponent } from '../search-by-denomination/search-by-denomination.component';
import { SearchByYearlyturnoverComponent } from '../search-by-yearlyturnover/search-by-yearlyturnover.component';
import { SearchByVatcodeComponent } from '../search-by-vatcode/search-by-vatcode.component';

@Component({
  selector: 'app-foradminuser',
  templateUrl: './foradminuser.component.html',
  styleUrl: './foradminuser.component.scss',
})
export class ForadminuserComponent {
  private customerSvc = inject(CustomerService);
  private modalService = inject(NgbModal);
  searchBy: string = 'all';
  customers: iCustomer[] = [];

  ngOnInit() {
    this.getAllCustomers(0, 25);
  }
  getAllCustomers(numberPage: number, size: number, type?: string[]) {
    this.customerSvc
      .getAllCustomers(numberPage, size, type)
      .subscribe((data) => {
        this.customers = data.content;
      });
  }

  openSearchByCreationDate() {
    const modalRef = this.modalService.open(SearchByCreationdateComponent, {
      size: 'xl',
    });
    modalRef.result.then((res) => {
      this.customers = res;
      console.log(this.customers);
    });
  }

  openSearchByLastContact() {
    const modalRef = this.modalService.open(SearchByLastcontactComponent, {
      size: 'xl',
    });
    modalRef.result.then((res) => {
      this.customers = res;
      console.log(this.customers);
    });
  }

  openSearchByDenomination() {
    const modalRef = this.modalService.open(SearchByDenominationComponent, {
      size: 'xl',
    });
    modalRef.result.then((res) => {
      this.customers = res;
      console.log(this.customers);
    });
  }

  openSearchByYearlyTurnover() {
    const modalRef = this.modalService.open(SearchByYearlyturnoverComponent, {
      size: 'xl',
    });
    modalRef.result.then((res) => {
      this.customers = res;
      console.log(this.customers);
    });
  }

  openSearchByVatcode() {
    const modalRef = this.modalService.open(SearchByVatcodeComponent, {
      size: 'xl',
    });
    modalRef.result.then((res) => {
      this.customers = res;
      console.log(this.customers);
    });
  }
}
