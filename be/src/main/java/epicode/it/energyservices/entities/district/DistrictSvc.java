package epicode.it.energyservices.entities.district;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@AllArgsConstructor
@Validated
public class DistrictSvc {
    private final DistrictRepo districtRepo;

    // restituisco tutti i Districts
    public List<District> findAllDistricts() {
        return districtRepo.findAll();
    }

    // restituisco un district cercando per id
    public District findDistrictById(Long id) {
        return districtRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Il district cercato non esiste"));
    }
}