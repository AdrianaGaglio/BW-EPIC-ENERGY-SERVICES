package epicode.it.energyservices.entities.address;

import lombok.Data;

@Data
public class AddressCreateRequest {
    private String street;
    private String addressNumber;
    private String location;
    private int cap;
    private Long idCity;
}
