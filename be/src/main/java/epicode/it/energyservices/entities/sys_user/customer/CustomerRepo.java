package epicode.it.energyservices.entities.sys_user.customer;

import epicode.it.energyservices.entities.sys_user.customer.dto.CustomerRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepo extends JpaRepository<Customer, Long> {

    boolean existsByVatCode(String vatCode);

    boolean existsByDenomination(String denomination);

    boolean existsByPec(String pec);

    boolean existsByPhone(String phone);


}
