package epicode.it.energyservices.entities.invoice_status;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class InvoiceStatusRequest {

    @NotNull(message = "Status name is required")
    @NotBlank(message = "Status name is required")
    private String name;

    @NotNull(message = "Status description is required")
    @NotBlank(message = "Status description is required")
    private String description;
}
