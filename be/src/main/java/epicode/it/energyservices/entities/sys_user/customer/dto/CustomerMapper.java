package epicode.it.energyservices.entities.sys_user.customer.dto;


import epicode.it.energyservices.entities.sys_user.customer.Customer;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerMapper {
    private ModelMapper modelMapper = new ModelMapper();

    public CustomerResponse toCustomerResponse(Customer e) {
        CustomerResponse customerResponse = modelMapper.map(e, CustomerResponse.class);
        return customerResponse;
    }

    public List<CustomerResponse> toCustomerResponseList(List<Customer> customers) {
        return customers.stream().map(this::toCustomerResponse).toList();
    }

    public CustomerResponseForInvoice toCustomerResponseForInvoice(Customer e) {
        CustomerResponseForInvoice customerResponseForInvoice = modelMapper.map(e, CustomerResponseForInvoice.class);
        return customerResponseForInvoice;
    }
}
