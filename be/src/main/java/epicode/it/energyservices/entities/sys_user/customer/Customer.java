package epicode.it.energyservices.entities.sys_user.customer;

import epicode.it.energyservices.entities.invoice.Invoice;
import epicode.it.energyservices.entities.sys_user.SysUser;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Data
@Entity
public class Customer extends SysUser {
    private String denomination;

    @Column(name="vat_code")
    private String vatCode;

    @Column(name="creation_date")
    private LocalDate creationDate = LocalDate.now();

    @Column(name="last_contact")
    private LocalDate lastContact;

    @Column(name="yearly_turnover")
    private double yearlyTurnover;

    private String pec;

    private String phone;

    @Column(name="contact_phone")
    private String contactPhone;

    private String image;

    @Enumerated(EnumType.STRING)
    private Type type;

    private HashMap<String, String> addresses = new HashMap<>(); // collegare a entità indirizzo

    @OneToMany
    private List<Invoice> invoices = new ArrayList<>();

}