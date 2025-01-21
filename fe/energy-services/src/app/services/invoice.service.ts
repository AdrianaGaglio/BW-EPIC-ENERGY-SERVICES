import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development';
import { Observable } from 'rxjs';
import { iInvoiceresponse } from '../interfaces/iinvoiceresponse';
import { iInvoiceresponseforcustomer } from '../interfaces/iinvoiceresponseforcustomer';
import { DecodeTokenService } from './decode-token.service';
import { iInvoiceupdaterequest } from '../interfaces/iinvoiceupdaterequest';
import { iInvoicerequest } from '../interfaces/iinvoicerequest';

@Injectable({
  providedIn: 'root',
})
export class InvoiceService {
  constructor(
    private http: HttpClient,
    private decodeToken: DecodeTokenService
  ) {}

  url: string = environment.baseUrl + '/invoice';

  getAll(): Observable<iInvoiceresponse[]> {
    return this.http.get<iInvoiceresponse[]>(this.url);
  }

  getAllPaged(): Observable<iInvoiceresponse[]> {
    return this.http.get<iInvoiceresponse[]>(this.url + '/paged');
  }

  getAllByCustomer(): Observable<iInvoiceresponseforcustomer[]> {
    return this.http.get<iInvoiceresponseforcustomer[]>(
      this.url + '/bycustomer'
    );
  }

  getById(id: number): Observable<any> {
    if (this.decodeToken.userRoles$.value.includes('ROLE_CUSTOMER')) {
      return this.http.get<iInvoiceresponseforcustomer>(
        `${this.url}/invoce/${id}`
      );
    }
    return this.http.get<iInvoiceresponse>(`${this.url}/${id}`);
  }

  getByNumber(number: number): Observable<any> {
    if (this.decodeToken.userRoles$.value.includes('ROLE_CUSTOMER')) {
      return this.http.get<iInvoiceresponseforcustomer>(
        `${this.url}/by-number/${number}`
      );
    }
    return this.http.get<iInvoiceresponse>(`${this.url}/by-number/${number}`);
  }

  updateStatus(
    number: number,
    request: iInvoiceupdaterequest
  ): Observable<iInvoiceresponse> {
    return this.http.put<iInvoiceresponse>(`${this.url}/${number}`, request);
  }

  create(request: iInvoicerequest): Observable<iInvoiceresponse> {
    return this.http.post<iInvoiceresponse>(this.url, request);
  }

  getAllByStatus(
    status: string,
    direction: string = 'ASC'
  ): Observable<iInvoiceresponse[]> {
    return this.http.get<iInvoiceresponse[]>(
      `${this.url}/by-status&status=${status}&direction=${direction}`
    );
  }
}
