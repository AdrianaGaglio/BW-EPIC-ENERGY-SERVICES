package epicode.it.energyservices.entities.sys_user.customer.dto;

import epicode.it.energyservices.entities.sys_user.customer.Type;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CustomerRequest {

    @NotBlank(message = "Denomination is required")
    private String denomination;

    @NotBlank(message = "Vat Code is required")
    private String vatCode;

    @NotBlank(message = "PEC Email is required")
    @Email(message = "PEC Email is not valid")
    private String pec;

    @NotBlank(message = "Phone is required")
    private String phone;

    private String contactPhone;

    private Type type;

    private String image;

//    aggiungere lista indirizzi
}
