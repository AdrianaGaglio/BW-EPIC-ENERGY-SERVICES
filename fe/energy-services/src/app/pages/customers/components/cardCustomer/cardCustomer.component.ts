import { iCustomer } from '../../../../interfaces/icustomer';
import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-card-customer',
  templateUrl: './cardCustomer.component.html',
  styleUrl: './cardCustomer.component.scss',
})
export class CardCustomerComponent {
  @Input() customer!: iCustomer;

  customerTest = {
    id: 1,
    denomination: 'Monti SPA',
    vatCode: '76507102565',
    creationDate: '2025-01-22',
    lastContact: null,
    yearlyTurnover: 100,
    pec: 'ferdinando.orlando@email.it',
    phone: '277.075.3131',
    contactPhone: '1-675-448-2515',
    type: 'SPA',
    addresses: {
      RegisteredOfficeAddress: {
        id: 5,
        street: 'Borgo Egisto 55, Appartamento 84',
        addressNumber: '890',
        cap: 30213,
        city: {
          id: 620,
          name: 'Montezemolo',
          district: {
            id: 32,
            name: 'Cuneo',
            code: 'CN',
            region: 'Piemonte',
          },
        },
      },
      OperationalHeadquartersAddress: {
        id: 6,
        street: 'Strada Alan 572',
        addressNumber: '106',
        cap: 87258,
        city: {
          id: 368,
          name: 'Rassa',
          district: {
            id: 106,
            name: 'Vercelli',
            code: 'VC',
            region: 'Piemonte',
          },
        },
      },
    },
    invoices: [
      {
        id: 29,
        date: '2025-02-02',
        amount: 211.4,
        number: 29,
        status: 'PAID',
      },
      {
        id: 33,
        date: '2025-02-07',
        amount: 526.22,
        number: 33,
        status: 'UNDER REVIEW',
      },
    ],
  };
}
