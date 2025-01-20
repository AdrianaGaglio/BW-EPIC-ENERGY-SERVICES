package epicode.it.energyservices.entities.sys_user.customer;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
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
}
