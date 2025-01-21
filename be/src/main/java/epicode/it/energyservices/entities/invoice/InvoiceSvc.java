package epicode.it.energyservices.entities.invoice;

import epicode.it.energyservices.entities.invoice.dto.InvoiceRequest;
import epicode.it.energyservices.entities.invoice.dto.InvoiceResponse;
import epicode.it.energyservices.entities.invoice.dto.InvoiceResponseMapper;
import epicode.it.energyservices.entities.invoice.dto.InvoiceUpdateRequest;
import epicode.it.energyservices.entities.invoice_status.InvoiceStatus;
import epicode.it.energyservices.entities.invoice_status.InvoiceStatusSvc;
import epicode.it.energyservices.entities.sys_user.customer.Customer;
import epicode.it.energyservices.entities.sys_user.customer.CustomerSvc;
import epicode.it.energyservices.entities.sys_user.customer.dto.CustomerResponse;
import epicode.it.energyservices.utils.email.EmailMapper;
import epicode.it.energyservices.utils.email.EmailSvc;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Validated
public class InvoiceSvc {
    private final InvoiceRepo invoiceRepo;
    private final InvoiceStatusSvc invoiceStatusSvc;
    private final CustomerSvc customerSvc;
    private final InvoiceResponseMapper mapper;
    private final EmailMapper emailMapper;
    private final EmailSvc emailSvc;

    public List<InvoiceResponse> getAll() {
        return mapper.toInvoiceResponseList(invoiceRepo.findAll());
    }

    public Page<InvoiceResponse> getAllPageable(Pageable pageable) {
        Page<Invoice> pagedInvoices = invoiceRepo.findAll(pageable);
        Page<InvoiceResponse> response = pagedInvoices.map(e -> {
            InvoiceResponse invoiceResponse = mapper.toInvoiceResponse(e);
            return invoiceResponse;
        });
        return response;
    }

    public Invoice getById(Long id) {
        return invoiceRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Invoice not found"));
    }

    public int count() {
        return (int) invoiceRepo.count();
    }

    @Transactional
    public InvoiceResponse create(@Valid InvoiceRequest request) {
        Invoice i = new Invoice();
        BeanUtils.copyProperties(request, i);
        int nextNumber = invoiceRepo.findMaxNumber().orElse(0) + 1;
        i.setNumber(nextNumber);
        InvoiceStatus status = request.getStatus() != null ? invoiceStatusSvc.findByName(request.getStatus()) : invoiceStatusSvc.findByName("DRAFT");
        Customer c = customerSvc.getById(request.getCustomerId());
        i.setCustomer(c);
        i.setStatus(status);

        InvoiceResponse response = mapper.toInvoiceResponse(invoiceRepo.save(i));

        if (!i.getStatus().getName().equals("DRAFT"))
            emailSvc.sendEmailHtml(emailMapper.fromInvoicetoEmailRequest("New invoice", i));
        return response;
    }

    public Invoice getByNumber(int number) {
        return invoiceRepo.findFirstByNumber(number).orElseThrow(() -> new EntityNotFoundException("Invoice not found"));
    }

    public InvoiceResponse updateStatus(int number, @Valid InvoiceUpdateRequest request) {
        Invoice i = getByNumber(number);
        InvoiceStatus newStatus = invoiceStatusSvc.findByName(request.getStatus().toUpperCase());
        i.setStatus(newStatus);
        InvoiceResponse response = mapper.toInvoiceResponse(invoiceRepo.save(i));
        emailSvc.sendEmailHtml(emailMapper.fromInvoicetoEmailRequest("Invoice status changed", i));
        return response;
    }

    public List<InvoiceResponse> getAllByStatus(String status, String direction) {
        InvoiceStatus invoiceStatus = invoiceStatusSvc.findByName(status.toUpperCase());
        if (direction.equals("ASC")) {
            return mapper.toInvoiceResponseList(invoiceRepo.findAllByStatusOrderByDateAsc(invoiceStatus.getId()));
        } else {
            return mapper.toInvoiceResponseList(invoiceRepo.findAllByStatusOrderByDateDesc(invoiceStatus.getId()));
        }
    }

    public List<InvoiceResponse> getAllByCustomer(Long customerId, String vatCode, String pec, String direction) {
        if (direction.equals("ASC")) {
            return mapper.toInvoiceResponseList(invoiceRepo.findAllByCustomerOrderByDateAsc(customerId, vatCode, pec));
        } else {
            return mapper.toInvoiceResponseList(invoiceRepo.findAllByCustomerOrderByDateDesc(customerId, vatCode, pec));
        }
    }

    public List<InvoiceResponse> getAllByDate(LocalDate date) {
        return mapper.toInvoiceResponseList(invoiceRepo.findAllByDate(date));
    }

    public List<InvoiceResponse> getAllByYear(int year) {
        return mapper.toInvoiceResponseList(invoiceRepo.findAllByYear(year));
    }

    public List<InvoiceResponse> getAllByAmountBetween(double min, double max) {
        return mapper.toInvoiceResponseList(invoiceRepo.findAllByAmountBetweenOrderByAmountAsc(min, max));
    }

}
