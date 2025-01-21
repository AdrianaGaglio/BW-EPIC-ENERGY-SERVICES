import { Icustomerpageresponse } from './../interfaces/icustomerpageresponse';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development';
import { iCustomer } from '../interfaces/icustomer';

@Injectable({
  providedIn: 'root',
})
export class CustomerService {
  baseUrl: string = environment.baseUrl + 'customer';

  constructor(private HttpClient: HttpClient) {}

  getAllCustomers(numberPage: number, size: number, type?: string[]) {
    let url: string = this.baseUrl + `/all?page=${numberPage}&size=${size}`;
    if (type) {
      type.forEach((t) => {
        url += `&sort=${t}`;
      });
    }
    return this.HttpClient.get<Icustomerpageresponse>(url);
  }

  getCustomersByCreationDate(startDate: Date, endDate: Date) {
    return this.HttpClient.get<iCustomer>(
      this.baseUrl +
        `/byCreationDateBetween?startDate=${startDate}&endDate=${endDate}`
    );
  }
  getCustomersByLastContact(startDate: Date, endDate: Date) {
    return this.HttpClient.get(
      this.baseUrl +
        `/byLastContactBetween?startDate=${startDate}&endDate=${endDate}`
    );
  }

  getCustomersByDenominationsContain(searchTerm: string) {
    return this.HttpClient.get<iCustomer>(
      this.baseUrl + `/byDenominationContaining?searchTerm=${searchTerm}`
    );
  }

  getCustomersByYearlyTurnover(min: number, max: number) {
    return this.HttpClient.get(
      this.baseUrl + `/byYearlyTurnoverBetween?min=${min}&max=${max}`
    );
  }
}
