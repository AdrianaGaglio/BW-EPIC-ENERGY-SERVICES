package epicode.it.energyservices.entities.invoice;

import epicode.it.energyservices.entities.invoice.dto.InvoiceRequest;
import epicode.it.energyservices.entities.invoice.dto.InvoiceResponse;
import epicode.it.energyservices.entities.invoice.dto.InvoiceResponseMapper;
import epicode.it.energyservices.entities.invoice.dto.InvoiceUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Response;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/invoice")
@RequiredArgsConstructor
public class InvoiceController {
    private final InvoiceSvc invoiceSvc;
    private final InvoiceResponseMapper mapper;

    @GetMapping
    public ResponseEntity<List<InvoiceResponse>> getAll() {
        return ResponseEntity.ok(invoiceSvc.getAll());
    }

    @GetMapping("/paged")
    public ResponseEntity<Page<InvoiceResponse>> getAllPaged(@ParameterObject Pageable pageable) {
        return ResponseEntity.ok(invoiceSvc.getAllPageable(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvoiceResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(mapper.toInvoiceResponse(invoiceSvc.getById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InvoiceResponse> updateStatus(@PathVariable Long id, @RequestBody InvoiceUpdateRequest request) {
        return ResponseEntity.ok(invoiceSvc.updateStatus(id, request));
    }

    @PostMapping
    public ResponseEntity<InvoiceResponse> create(@RequestBody InvoiceRequest request) {
        return new ResponseEntity<>(invoiceSvc.create(request), HttpStatus.CREATED);
    }

    @GetMapping("/by-status")
    public ResponseEntity<List<InvoiceResponse>> getAllByStatus(@RequestParam String status, @RequestParam(required = false) String direction) {
        if (direction == null || direction.isEmpty()) direction = "ASC";
        return ResponseEntity.ok(invoiceSvc.getAllByStatus(status, direction));
    }

    @GetMapping("/by-customer")
    public ResponseEntity<List<InvoiceResponse>> getAllByCustomer(@RequestParam(required = false) Long customerId,
                                                                  @RequestParam(required = false) String vatCode,
                                                                  @RequestParam(required = false) String pec,
                                                                  @RequestParam(required = false) String direction) {
        if (customerId == null && vatCode == null && pec == null) {
            throw new InvalidParameterException("Customer ID, VAT Code or PEC is required");
        }
        if (direction == null || direction.isEmpty()) direction = "ASC";
        return ResponseEntity.ok(invoiceSvc.getAllByCustomer(customerId, vatCode, pec, direction));
    }

    @GetMapping("/by-date")
    public ResponseEntity<List<InvoiceResponse>> getAllByDate(@RequestParam LocalDate date) {
        return ResponseEntity.ok(invoiceSvc.getAllByDate(date));
    }

    @GetMapping("/by-year")
    public ResponseEntity<List<InvoiceResponse>> getAllByYear(@RequestParam int year) {
        return ResponseEntity.ok(invoiceSvc.getAllByYear(year));
    }

    @GetMapping("/amount-range")
    public ResponseEntity<List<InvoiceResponse>> getAllByAmountBetween(double min, double max) {
        return ResponseEntity.ok(invoiceSvc.getAllByAmountBetween(min, max));
    }
}
