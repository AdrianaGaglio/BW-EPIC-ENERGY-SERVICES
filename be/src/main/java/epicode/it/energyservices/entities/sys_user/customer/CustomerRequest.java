package epicode.it.energyservices.entities.sys_user.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.HashMap;

@Data
public class CustomerRequest {

    @NotNull(message = "Denomination is required")
    @NotBlank(message = "Denomination is required")
    private String denomination;

    @NotNull(message = "Vat Code is required")
    @NotBlank(message = "Vat Code is required")
    private String vatCode;

    @NotNull(message = "PEC Email is required")
    @NotBlank(message = "PEC Email is required")
    @Email(message = "PEC Email is not valid")
    private String pec;

    @NotNull(message = "Phone is required")
    @NotBlank(message = "Phone is required")
    private String phone;

    private String contactPhone;

    private String image;

    private String type;

//    aggiungere lista indirizzi
}
