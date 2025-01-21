package epicode.it.energyservices.entities.sys_user.customer;

import epicode.it.energyservices.entities.sys_user.customer.dto.CustomerResponse;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    @Autowired
    private CustomerSvc customerSvc;

    @GetMapping("/all")
    public Page<CustomerResponse> getAll(@ParameterObject Pageable pageable) {
        return customerSvc.getAllPageable(pageable);
    }

    @GetMapping("/byYearlyTurnoverBetween")
    public List<CustomerResponse> getByYearlyTurnoverBetween(@Param("min") double min,@Param("max") double max) {
        return customerSvc.getByYearlyTurnoverBetween(min, max);
    }

    @GetMapping("/byDenominationContaining")
    public List<CustomerResponse> getByDenominationContaining(@Param("searchTerm") String searchTerm) {
        return customerSvc.getByDenominationContaining(searchTerm.toLowerCase());
    }

    @GetMapping("/byCreationDateBetween")
    public List<CustomerResponse> getByCreationDateBetween(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate) {
        return customerSvc.getByCreationDateBetween(startDate, endDate);
    }

    @GetMapping("/byLastContactBetween")
    public List<CustomerResponse> getByLastContactBetween(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate) {
        return customerSvc.getByLastContactBetween(startDate, endDate);
    }

}
