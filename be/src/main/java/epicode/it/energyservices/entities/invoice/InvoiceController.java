package epicode.it.energyservices.entities.invoice;

import epicode.it.energyservices.entities.invoice.dto.InvoiceUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoice")
@RequiredArgsConstructor
public class InvoiceController {
    private final InvoiceSvc invoiceSvc;

    @GetMapping
    public ResponseEntity<List<Invoice>> getAll() {
        return ResponseEntity.ok(invoiceSvc.getAll());
    }

    @GetMapping("/paged")
    public ResponseEntity<Page<Invoice>> getAllPaged(@ParameterObject Pageable pageable) {
        return ResponseEntity.ok(invoiceSvc.getAllPageable(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Invoice> updateStatus(@PathVariable Long id, @RequestBody InvoiceUpdateRequest request) {
        return ResponseEntity.ok(invoiceSvc.updateStatus(id, request));
    }


}
