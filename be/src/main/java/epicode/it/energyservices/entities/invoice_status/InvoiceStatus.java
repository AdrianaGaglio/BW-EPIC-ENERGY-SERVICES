package epicode.it.energyservices.entities.invoice_status;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="invoice_status")
public class InvoiceStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private  Long id;

    private String name;
}