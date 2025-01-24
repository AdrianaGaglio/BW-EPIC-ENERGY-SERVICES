import { Component } from '@angular/core';
import { Customer } from './interfaces/iinvoicepageresponse';
import { CustomerService } from './services/customer.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss',
})
export class AppComponent {
  constructor(private customerSvc: CustomerService) {
    this.customerSvc.getAll().subscribe();
  }
  title = 'energy-services';
}
