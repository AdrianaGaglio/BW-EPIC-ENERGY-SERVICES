import { Component } from '@angular/core';
import { CustomerService } from '../../services/customer.service';
import { DecodeTokenService } from '../../services/decode-token.service';
import { iCustomer } from '../../interfaces/icustomer';
import { Icustomerpageresponse } from '../../interfaces/icustomerpageresponse';

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrl: './customers.component.scss',
})
export class CustomersComponent {
  constructor(
    private customerSvc: CustomerService,
    private decodeToken: DecodeTokenService
  ) {}
  roles: string[] = [];
  isCustomer: boolean = false;

  ngOnInit(): void {
    this.roles = this.decodeToken.userRoles$.getValue();
    console.log(this.roles);

    if (this.roles.includes('CUSTOMER')) {
      this.isCustomer = true;
    }
  }
}
