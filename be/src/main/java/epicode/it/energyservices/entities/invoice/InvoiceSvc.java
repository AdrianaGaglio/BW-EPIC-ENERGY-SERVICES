package epicode.it.energyservices.entities.invoice;

import epicode.it.energyservices.entities.invoice.dto.InvoiceRequest;
import epicode.it.energyservices.entities.invoice_status.InvoiceStatusSvc;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@RequiredArgsConstructor
@Validated
public class InvoiceSvc {
    private final InvoiceRepo invoiceRepo;
    private final InvoiceStatusSvc invoiceStatusSvc;

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
        i.setStatus(invoiceStatusSvc.findByName(request.getStatus()));
        return invoiceRepo.save(i);
    }
}
