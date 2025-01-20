package epicode.it.energyservices.entities.invoice;

import epicode.it.energyservices.entities.invoice_status.InvoiceStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InvoiceRepo extends JpaRepository<Invoice, Long> {


}
