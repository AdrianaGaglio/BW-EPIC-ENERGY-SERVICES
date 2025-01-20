package epicode.it.energyservices.entities.sys_user.customer;

import epicode.it.energyservices.entities.sys_user.customer.dto.CustomerResponse;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

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

}
