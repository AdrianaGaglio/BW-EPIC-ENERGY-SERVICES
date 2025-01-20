package epicode.it.energyservices.entities.invoice_status;

import epicode.it.energyservices.entities.invoice_status.dto.InvoiceStatusRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/invoice_status")
@RequiredArgsConstructor
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

}
