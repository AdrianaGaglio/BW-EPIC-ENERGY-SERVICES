package epicode.it.energyservices.entities.district;

import epicode.it.energyservices.entities.address.Address;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/districts")
public class DistrictController {

    private final DistrictSvc districtSvc;
    @GetMapping("/{id}")
    public ResponseEntity<District> getDistrictById(@PathVariable Long id){
        return ResponseEntity.ok(districtSvc.findDistrictById(id));
    }

    @GetMapping
    public ResponseEntity<List<District>> getAllDistricts(){
        return ResponseEntity.ok(districtSvc.findAllDistricts());
    }
}
