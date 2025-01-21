package epicode.it.energyservices.entities.invoice;

import epicode.it.energyservices.entities.invoice.dto.InvoiceRequest;
import epicode.it.energyservices.entities.invoice.dto.InvoiceUpdateRequest;
import epicode.it.energyservices.entities.invoice_status.InvoiceStatus;
import epicode.it.energyservices.entities.invoice_status.InvoiceStatusSvc;
import epicode.it.energyservices.entities.sys_user.customer.Customer;
import epicode.it.energyservices.entities.sys_user.customer.CustomerSvc;
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

    public List<Invoice> getAll() {
        return invoiceRepo.findAll();
    }

    public Page<Invoice> getAllPageable(Pageable pageable) {
        return invoiceRepo.findAll(pageable);
    }

    public Invoice getById(Long id) {
        return invoiceRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Invoice not found"));
    }

    public int count() {
        return (int) invoiceRepo.count();
    }

    public String delete(Long id) {
        Invoice e = getById(id);
        invoiceRepo.delete(e);
        return "Invoice deleted successfully";
    }

    public String delete(Invoice e) {
        Invoice foundInvoice = getById(e.getId());
        invoiceRepo.delete(foundInvoice);
        return "Invoice deleted successfully";
    }

    @Transactional
    public Invoice create(@Valid InvoiceRequest request) {
        Invoice i = new Invoice();
        BeanUtils.copyProperties(request, i);
        int nextNumber = invoiceRepo.findMaxNumber().orElse(0) + 1;
        i.setNumber(nextNumber);
        InvoiceStatus status = request.getStatus() != null ? invoiceStatusSvc.findByName(request.getStatus()) : invoiceStatusSvc.findByName("DRAFT");
        Customer c = customerSvc.getById(request.getCustomerId());
        i.setCustomer(c);
        i.setStatus(status);
        return invoiceRepo.save(i);
    }

    public Invoice updateStatus(Long id, @Valid InvoiceUpdateRequest request) {
        Invoice i = getById(id);
        InvoiceStatus newStatus = invoiceStatusSvc.findByName(request.getStatus().toUpperCase());
        i.setStatus(newStatus);
        return invoiceRepo.save(i);
    }

    public List<Invoice> getAllByStatus(String status, String direction) {
        InvoiceStatus invoiceStatus = invoiceStatusSvc.findByName(status.toUpperCase());
        if (direction.equals("ASC")) {
            return invoiceRepo.findAllByStatusOrderByDateAsc(invoiceStatus.getId());
        } else {
            return invoiceRepo.findAllByStatusOrderByDateDesc(invoiceStatus.getId());
        }
    }

    public List<Invoice> getAllByCustomer(Long customerId, String direction) {
        if (direction.equals("ASC")) {
            return invoiceRepo.findAllByCustomerOrderByDateAsc(customerId);
        } else {
            return invoiceRepo.findAllByCustomerOrderByDateDesc(customerId);
        }

    }

    public List<Invoice> getAllByDate(LocalDate date) {
        return invoiceRepo.findAllByDate(date);
    }

    public List<Invoice> getAllByYear(int year) {
        return invoiceRepo.findAllByYear(year);
    }

    public List<Invoice> getAllByAmountBetween(double min, double max) {
        return invoiceRepo.findAllByAmountBetweenOrderByAmountAsc(min, max);
    }


}
