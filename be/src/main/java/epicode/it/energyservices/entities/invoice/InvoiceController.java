package epicode.it.energyservices.entities.invoice;

import epicode.it.energyservices.entities.invoice.dto.InvoiceRequest;
import epicode.it.energyservices.entities.invoice.dto.InvoiceUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Response;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    @PostMapping
    public ResponseEntity<Invoice> create(@RequestBody InvoiceRequest request) {
        return new ResponseEntity<>(invoiceSvc.create(request), HttpStatus.CREATED);
    }

    @GetMapping("/by-status")
    public ResponseEntity<List<Invoice>> getAllByStatus(@RequestParam String status, String direction) {
        if (direction == null || direction.isEmpty()) direction = "ASC";
        return ResponseEntity.ok(invoiceSvc.getAllByStatus(status, direction));
    }

    @GetMapping("/by-customer")
    public ResponseEntity<List<Invoice>> getAllByCustomer(@RequestParam Long customerId, String direction) {
        if (direction == null || direction.isEmpty()) direction = "ASC";
        return ResponseEntity.ok(invoiceSvc.getAllByCustomer(customerId, direction));
    }

    @GetMapping("/by-date")
    public ResponseEntity<List<Invoice>> getAllByDate(@RequestParam LocalDate date) {
        return ResponseEntity.ok(invoiceSvc.getAllByDate(date));
    }

    @GetMapping("/by-year")
    public ResponseEntity<List<Invoice>> getAllByYear(@RequestParam int year) {
        return ResponseEntity.ok(invoiceSvc.getAllByYear(year));
    }

    @GetMapping("/amount-range")
    public ResponseEntity<List<Invoice>> getAllByAmountBetween(double min, double max) {
        return ResponseEntity.ok(invoiceSvc.getAllByAmountBetween(min, max));
    }
}
