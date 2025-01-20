package epicode.it.energyservices.entities.customer;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private  Long id;


}