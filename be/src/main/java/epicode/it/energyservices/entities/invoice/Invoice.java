package epicode.it.energyservices.entities.invoice;

import epicode.it.energyservices.entities.invoice_status.InvoiceStatus;
import epicode.it.energyservices.entities.sys_user.customer.Customer;
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

    @Column(nullable = false, unique = true)
    @SequenceGenerator(name = "invoice_seq", sequenceName = "invoice_sequence", allocationSize = 1)
    private int number;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private InvoiceStatus status;

}