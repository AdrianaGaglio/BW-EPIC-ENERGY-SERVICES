package epicode.it.energyservices.entities.sys_user.customer;

import epicode.it.energyservices.entities.sys_user.customer.dto.CustomerMapper;
import epicode.it.energyservices.entities.sys_user.customer.dto.CustomerResponse;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/customer")
@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
public class CustomerController {
    @Autowired
    private CustomerSvc customerSvc;

    @Autowired
    private CustomerMapper mapper;

    @GetMapping("/all")
//    Accessibile solo ad ADMIN/USER
    public ResponseEntity<Page<CustomerResponse>> getAll(@ParameterObject Pageable pageable) {
        return ResponseEntity.ok(customerSvc.getAllPageable(pageable));
    }

    @GetMapping("/byYearlyTurnoverBetween")
    //    Accessibile solo ad ADMIN/USER
    public ResponseEntity<List<CustomerResponse>> getByYearlyTurnoverBetween(@Param("min") double min,@Param("max") double max) {
        return ResponseEntity.ok(customerSvc.getByYearlyTurnoverBetween(min, max));
    }

    @GetMapping("/byDenominationContaining")
    //    Accessibile solo ad ADMIN/USER
    public ResponseEntity<List<CustomerResponse>> getByDenominationContaining(@Param("searchTerm") String searchTerm) {
        return ResponseEntity.ok(customerSvc.getByDenominationContaining(searchTerm.toLowerCase()));
    }

    @GetMapping("/byCreationDateBetween")
    //    Accessibile solo ad ADMIN/USER
    public ResponseEntity<List<CustomerResponse>> getByCreationDateBetween(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate) {
        return ResponseEntity.ok(customerSvc.getByCreationDateBetween(startDate, endDate));
    }

    @GetMapping("/byLastContactBetween")
    //    Accessibile solo ad ADMIN/USER
    public ResponseEntity<List<CustomerResponse>> getByLastContactBetween(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate) {
        return ResponseEntity.ok(customerSvc.getByLastContactBetween(startDate, endDate));
    }

    @GetMapping("/by-username")
    public ResponseEntity<CustomerResponse> getByUsername(@RequestParam String username) {
        return ResponseEntity.ok(mapper.toCustomerResponse(customerSvc.getByUsername(username)));
    }

    @GetMapping("/by-vatCode")
    public ResponseEntity<CustomerResponse> getByVatCode(@RequestParam String vatCode) {
        return ResponseEntity.ok(customerSvc.findByVatCode(vatCode));
    }
}
