package epicode.it.energyservices.entities.address;

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
@PreAuthorize("isAuthenticated()")
public class AddressController {
    private final AddressSvc addressService;

    @GetMapping
    public ResponseEntity<List<Address>> getAllAddresses(){
        return ResponseEntity.ok(addressService.findAllAddress());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAddress(@PathVariable Long id,  @AuthenticationPrincipal UserDetails userDetails){
        addressService.deleteAddress(id, userDetails);
        return new ResponseEntity<>("address eliminato", HttpStatus.NO_CONTENT);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable Long id){
        return ResponseEntity.ok(addressService.findAddressById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Address> modifyAddress(@RequestBody AddressCreateRequest addressCreaRequest, @PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails){
        return ResponseEntity.ok(addressService.updateAddress(id,addressCreaRequest, userDetails));
    }
    @PostMapping
    public ResponseEntity<Address> createAddress(@RequestBody AddressCreateRequest addressCreateRequest, @AuthenticationPrincipal UserDetails userDetails) {
        Address savedAddress = addressService.saveAddress(addressCreateRequest, userDetails);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAddress);
    }


}
