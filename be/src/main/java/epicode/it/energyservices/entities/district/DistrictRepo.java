package epicode.it.energyservices.entities.district;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DistrictRepo extends JpaRepository<District,Long> {
    Optional<District> findByName(String name);
}
