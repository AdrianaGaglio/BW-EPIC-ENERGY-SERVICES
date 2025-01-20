package epicode.it.energyservices.entities.sys_user.customer;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@RequiredArgsConstructor
@Validated
public class CustomerSvc {
    private final CustomerRepo customerRepo;

    public List<Customer> getAll() {

        return customerRepo.findAll();
    }

    public Page<Customer> getAllPageable(Pageable pageable) {

        return customerRepo.findAll(pageable);
    }

    public Customer getById(Long id) {
        return customerRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Customer not found"));
    }

    public int count() {

        return (int) customerRepo.count();
    }

    public String delete(Long id) {
        Customer e = getById(id);
        customerRepo.delete(e);
        return "Customer deleted successfully";
    }

    public String delete(Customer e) {
        Customer foundCustomer = getById(e.getId());
        customerRepo.delete(foundCustomer);
        return "Customer deleted successfully";
    }

    public Customer create(@Valid CustomerRequest request) {
        if (customerRepo.existsByVatCode(request.getVatCode()))
            throw new EntityExistsException("Customer vatCode already exists");
        if (customerRepo.existsByDenomination(request.getDenomination()))
            throw new EntityExistsException("Customer denomination already exists");
        if (customerRepo.existsByPec(request.getPec())) throw new EntityExistsException("Customer pec already exists");
        if (customerRepo.existsByPhone(request.getPhone()))
            throw new EntityExistsException("Customer phone already exists");

        Customer c = new Customer();
        BeanUtils.copyProperties(request, c);
        // leggere indirizzi e mettere nell'hashmap

        return customerRepo.save(c);

    }
}
