package epicode.it.energyservices.entities.invoice;

import epicode.it.energyservices.entities.invoice_status.InvoiceStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name="invoices")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private  Long id;

    private LocalDate date;

    private double amount;

//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int number;

    @ManyToOne
    private InvoiceStatus status;

}