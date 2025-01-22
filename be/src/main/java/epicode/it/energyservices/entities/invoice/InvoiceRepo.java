package epicode.it.energyservices.entities.invoice;

import epicode.it.energyservices.entities.invoice_status.InvoiceStatus;
import epicode.it.energyservices.entities.sys_user.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface InvoiceRepo extends JpaRepository<Invoice, Long> {

    @Query("SELECT i FROM Invoice i WHERE i.status.id = :statusId ORDER BY i.date ASC")
    public List<Invoice> findAllByStatusOrderByDateAsc(@Param("statusId") Long statusId);

    @Query("SELECT i FROM Invoice i WHERE i.status.id = :statusId ORDER BY i.date DESC")
    public List<Invoice> findAllByStatusOrderByDateDesc(@Param("statusId") Long statusId);

    @Query("SELECT i FROM Invoice i WHERE i.customer.id = :customerId OR i.customer.vatCode = :vatCode OR i.customer.pec = :pec ORDER BY i.date ASC")
    public List<Invoice> findAllByCustomerOrderByDateAsc(@Param("customerId") Long customerId, @Param(("vatCode")) String vatCode, @Param("pec") String pec);

    @Query("SELECT i FROM Invoice i WHERE i.customer.id = :customerId OR i.customer.vatCode = :vatCode OR i.customer.pec = :pec ORDER BY i.date DESC")
    public List<Invoice> findAllByCustomerOrderByDateDesc(@Param("customerId") Long customerId, @Param(("vatCode")) String vatCode, @Param("pec") String pec);

    public List<Invoice> findAllByDate(LocalDate date);

    @Query("SELECT i FROM Invoice i WHERE EXTRACT(YEAR FROM i.date) = :year")
    public List<Invoice> findAllByYear(@Param("year") int year);

    public List<Invoice> findAllByAmountBetweenOrderByAmountAsc(double min, double max);

    public List<Invoice> findAllByCustomerId(Long customerId);

    @Query("SELECT MAX(i.number) FROM Invoice i")
    Optional<Integer> findMaxNumber();

    Optional<Invoice> findFirstByNumber(int number);

    @Query("SELECT i FROM Invoice i WHERE i.customer.vatCode = :vatCode")
    public List<Invoice> findAllByVatCode(@Param("vatCode") String vatCode);

    @Query("SELECT SUM(i.amount) FROM Invoice i WHERE i.customer.id = :customerId AND EXTRACT(YEAR FROM i.date) = :year")
    public double findTotalAllByCustomerAndYear(@Param("customerId") Long customerId, @Param("year") int year);
}

