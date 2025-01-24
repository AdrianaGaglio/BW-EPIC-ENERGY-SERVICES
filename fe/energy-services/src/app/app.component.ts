import { Component } from '@angular/core';
import { Customer } from './interfaces/iinvoicepageresponse';
import { CustomerService } from './services/customer.service';
import { AuthsrvService } from './auth/authsrv.service';
import { DecodeTokenService } from './services/decode-token.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss',
})
export class AppComponent {
  constructor(
    private customerSvc: CustomerService,
    private authSvc: AuthsrvService,
    private decodeToken: DecodeTokenService
  ) {
    if (this.authSvc.userAuthSubject$.getValue()) {
      this.decodeToken.userRoles$.subscribe((res) => {
        if (!res.includes('CUSTOMER')) {
          this.customerSvc.getAll().subscribe();
        }
      });
    }
  }
  title = 'energy-services';
}
