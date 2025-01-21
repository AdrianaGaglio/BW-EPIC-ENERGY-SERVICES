import { iAddress } from './../auth/interfaces/i-address';
import { iInvoice } from './iinvoice';

export interface iCustomer {
  denomination: string;
  vatCode: string;
  creationDate: string;
  lastContact: string;
  yearlyTurnover: number;
  pec: string;
  phone: string;
  contactPhone: string;
  type: string;
  iAddress: iAddress[];
  invoices: iInvoice[];
}
