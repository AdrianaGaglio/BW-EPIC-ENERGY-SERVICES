package epicode.it.energyservices.entities.sys_user.customer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer, Long> {

    boolean existsByVatCode(String vatCode);

    boolean existsByDenomination(String denomination);

    boolean existsByPec(String pec);

    boolean existsByPhone(String phone);
}
