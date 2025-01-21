import { iCustomerresponseforinvoice } from './icustomerresponseforinvoice';

export interface iInvoiceresponse {
  id: number;
  date: string;
  amount: number;
  status: string;
  customer: iCustomerresponseforinvoice;
}
