package epicode.it.energyservices.entities.address;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/addresses")
@PreAuthorize("isAuthenticated()")
public class AddressController {
    private final AddressService addressService;
}
