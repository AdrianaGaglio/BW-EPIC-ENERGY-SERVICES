package epicode.it.energyservices.entities.invoice;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceSvc {
    private final InvoiceRepo invoiceRepo;

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
}
