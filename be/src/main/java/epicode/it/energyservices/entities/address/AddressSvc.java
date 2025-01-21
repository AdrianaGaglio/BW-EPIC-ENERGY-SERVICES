package epicode.it.energyservices.entities.address;

import epicode.it.energyservices.entities.address.dto.AddressCreateRequest;
import epicode.it.energyservices.entities.city.City;
import epicode.it.energyservices.entities.city.CityRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@AllArgsConstructor
@Validated
public class AddressSvc {
    private final AddressRepo addressRepo;
    private final CityRepo cityRepo;

    // restituisco tutti gli adresses
    public List<Address> findAllAddress() {
        return addressRepo.findAll();
    }

    // restituisco un address cercando per id
    public Address findAddressById(Long id) {
        return addressRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("The district you are looking for does not exist"));
    }

    // cancella un address per id
    public void deleteAddress(Long id,  @AuthenticationPrincipal UserDetails userDetail) {
        if (!addressRepo.existsById(id)) {
            throw new EntityNotFoundException("The address indicated does not exist");
        }
        addressRepo.deleteById(id);
    }

    // salva un nuovo Address
    public Address saveAddress(Address request) {
/*        City city = cityRepo.findById(request.getIdCity())
                .orElseThrow(() -> new EntityNotFoundException("The city you are looking for does not exist"));

        Address newAddress = new Address();
        newAddress.setStreet(request.getStreet());
        newAddress.setAddressNumber(request.getAddressNumber());
        newAddress.setLocation(request.getLocation());
        newAddress.setCap(request.getCap());
        newAddress.setCity(city);*/
        return addressRepo.save(request);
    }

    // modifica un address esistente
    public Address updateAddress(Long id, AddressCreateRequest updatedRequest, @AuthenticationPrincipal UserDetails userDetails ) {

        Address existingAddress = addressRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("The address you are looking for does not exist"));

        City city = cityRepo.findById(updatedRequest.getIdCity())
                .orElseThrow(() -> new EntityNotFoundException("The city you are looking for does not exist"));

        existingAddress.setStreet(updatedRequest.getStreet());
        existingAddress.setAddressNumber(updatedRequest.getAddressNumber());
        existingAddress.setCap(updatedRequest.getCap());
        existingAddress.setCity(city);

        return addressRepo.save(existingAddress);
    }


}
