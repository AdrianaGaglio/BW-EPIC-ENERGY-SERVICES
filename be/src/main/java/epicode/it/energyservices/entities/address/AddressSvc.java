package epicode.it.energyservices.entities.address;

import epicode.it.energyservices.entities.city.City;
import epicode.it.energyservices.entities.city.CityRepo;
import epicode.it.energyservices.entities.district.District;
import epicode.it.energyservices.entities.district.DistrictRepo;
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
                .orElseThrow(() -> new EntityNotFoundException("Il district cercato non esiste"));
    }

    // cancella un address per id
    public void deleteAddress(Long id,  @AuthenticationPrincipal UserDetails userDetail) {
        if (!addressRepo.existsById(id)) {
            throw new EntityNotFoundException("L'indirizzo indicato non esiste");
        }
        addressRepo.deleteById(id);
    }

    // salva un nuovo Address
    public Address saveAddress(AddressCreateRequest request, @AuthenticationPrincipal UserDetails userDetails) {
        City city = cityRepo.findById(request.getIdCity())
                .orElseThrow(() -> new EntityNotFoundException("La città cercata non esiste"));

        Address newAddress = new Address();
        newAddress.setStreet(request.getStreet());
        newAddress.setAddressNumber(request.getAddressNumber());
        newAddress.setLocation(request.getLocation());
        newAddress.setCap(request.getCap());
        newAddress.setCity(city);
        return addressRepo.save(newAddress);
    }

    // modifica un address esistente
    public Address updateAddress(Long id, AddressCreateRequest updatedRequest,@AuthenticationPrincipal UserDetails userDetails ) {

        Address existingAddress = addressRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("L'indirizzo cercato non esiste"));

        City city = cityRepo.findById(updatedRequest.getIdCity())
                .orElseThrow(() -> new EntityNotFoundException("La città cercata non esiste"));

        existingAddress.setStreet(updatedRequest.getStreet());
        existingAddress.setAddressNumber(updatedRequest.getAddressNumber());
        existingAddress.setLocation(updatedRequest.getLocation());
        existingAddress.setCap(updatedRequest.getCap());
        existingAddress.setCity(city);

        return addressRepo.save(existingAddress);
    }


}
