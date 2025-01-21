package epicode.it.energyservices.entities.address;

import epicode.it.energyservices.entities.address.dto.AddressCreateRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/addresses")
public class AddressController {
    private final AddressSvc addressService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') || hasRole('USER') ")
    public ResponseEntity<List<Address>> getAllAddresses(){
        return ResponseEntity.ok(addressService.findAllAddress());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteAddress(@PathVariable Long id,  @AuthenticationPrincipal UserDetails userDetails){
        addressService.deleteAddress(id, userDetails);
        return new ResponseEntity<>("address eliminato", HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') || hasRole('USER') ")
    public ResponseEntity<Address> getAddressById(@PathVariable Long id){
        return ResponseEntity.ok(addressService.findAddressById(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') || hasRole('USER') ")
    public ResponseEntity<Address> modifyAddress(@RequestBody AddressCreateRequest addressCreaRequest, @PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails){
        return ResponseEntity.ok(addressService.updateAddress(id,addressCreaRequest, userDetails));
    }

    // verificare se necessario (gestione indirizzi a partire dalla creazione utente)
/*    @PostMapping
    public ResponseEntity<Address> createAddress(@RequestBody AddressCreateRequest addressCreateRequest, @AuthenticationPrincipal UserDetails userDetails) {
        Address savedAddress = addressService.saveAddress(addressCreateRequest, userDetails);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAddress);
    }*/


}
