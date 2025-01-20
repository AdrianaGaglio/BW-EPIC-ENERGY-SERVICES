package epicode.it.energyservices.entities.sys_user.customer.dto;

import epicode.it.energyservices.entities.invoice.Invoice;
import epicode.it.energyservices.entities.sys_user.customer.Type;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Data
public class CustomerResponse {
    private String denomination;
    private String vatCode;
    private LocalDate creationDate;
    private LocalDate lastContact;
    private double yearlyTurnover;
    private String pec;
    private String phone;
    private String contactPhone;
    private Type type;
    private HashMap<String, String> addresses = new HashMap<>(); // collegare a entità indirizzo
    private List<Invoice> invoices = new ArrayList<>();

}
