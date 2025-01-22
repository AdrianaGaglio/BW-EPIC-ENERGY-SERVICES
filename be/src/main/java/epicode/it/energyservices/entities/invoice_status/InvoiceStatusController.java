package epicode.it.energyservices.entities.invoice_status;

import epicode.it.energyservices.entities.invoice_status.dto.InvoiceStatusRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoice_status")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class InvoiceStatusController {
    private final InvoiceStatusSvc invoiceStatusSvc;

    @PostMapping
    public ResponseEntity<InvoiceStatus> create(InvoiceStatusRequest request) {
        return new ResponseEntity<>(invoiceStatusSvc.create(request), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(Long id) {
        return new ResponseEntity<>(invoiceStatusSvc.delete(id), HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<InvoiceStatus>> getAll() {
        return ResponseEntity.ok(invoiceStatusSvc.getAll());
    }

}
