import { iAddress } from '../auth/interfaces/i-address';

export interface iCustomerresponseforinvoice {
  denomination: string;
  vatCode: string;
  creationDate: string;
  lastContact: string | null;
  yearlyTurnover: number;
  pec: string;
  phone: string;
  contactPhone: string;
  type: string;
  addresses: {
    RegisteredOfficeAddress: iAddress;
    OperationalHeadquartersAddress: iAddress;
  };
}
