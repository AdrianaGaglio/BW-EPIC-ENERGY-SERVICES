import { Component, inject, Input } from '@angular/core';
import { iInvoiceresponse } from '../../interfaces/iinvoiceresponse';
import { DecodeTokenService } from '../../services/decode-token.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { UpdateInvoiceComponent } from './update-invoice/update-invoice.component';

@Component({
  selector: 'app-invoice-card',
  templateUrl: './invoice-card.component.html',
  styleUrl: './invoice-card.component.scss',
})
export class InvoiceCardComponent {
  constructor(private decodeToken: DecodeTokenService) {}
  private modalService = inject(NgbModal);

  @Input() invoice!: iInvoiceresponse;

  roles: string[] = [];

  ngOnInit() {
    this.decodeToken.userRoles$.subscribe((res) => (this.roles = res));
  }

  openModal(invoice: iInvoiceresponse) {
    const modalRef = this.modalService.open(UpdateInvoiceComponent, {
      size: 'xl',
    });

    modalRef.componentInstance.invoice = invoice;

    modalRef.result.then((res) => {
      this.invoice = res;
    });
  }
}
