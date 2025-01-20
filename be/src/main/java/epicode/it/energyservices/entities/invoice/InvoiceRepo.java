package epicode.it.energyservices.entities.invoice;

import epicode.it.energyservices.entities.invoice_status.InvoiceStatus;
import epicode.it.energyservices.entities.sys_user.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface InvoiceRepo extends JpaRepository<Invoice, Long> {

    @Query("SELECT i FROM Invoice i WHERE i.status.id = :statusId ORDER BY i.date :direction")
    public List<Invoice> findAllByStatusOrderByDate(@Param("statusId") Long statusId, @Param("direction") String direction);

    @Query("SELECT i FROM Invoce i WHERE i.customer.id = :customerId ORDER BY i.date :direction")
    public List<Invoice> findAllByCustomerOrderByDate(@Param("customerId") Long customerId, @Param("direction") String direction);

    public List<Invoice> findAllByDate(LocalDate date);

    @Query("SELECT i FROM Invoice i WHERE EXTRACT(YEAR FROM i.date) = :year")
    public List<Invoice> findAllByYear(int year);
}
