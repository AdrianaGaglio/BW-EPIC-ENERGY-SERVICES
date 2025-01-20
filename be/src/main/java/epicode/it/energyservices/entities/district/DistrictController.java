package epicode.it.energyservices.entities.district;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/districts")
@PreAuthorize("isAuthenticated()")
public class DistrictController {
}
