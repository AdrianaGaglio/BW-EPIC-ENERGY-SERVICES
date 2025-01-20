package epicode.it.energyservices.entities.address;

import epicode.it.energyservices.entities.city.City;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String street;
    private String addressNumber;
    private String location;
    private int cap;
    @OneToOne
    @JoinColumn(name = "city_id", referencedColumnName = "id")
    private City city;



}