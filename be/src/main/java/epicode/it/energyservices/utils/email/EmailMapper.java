package epicode.it.energyservices.utils.email;

import epicode.it.energyservices.auth.AppUser;
import epicode.it.energyservices.entities.invoice.Invoice;
import epicode.it.energyservices.entities.invoice.dto.InvoiceResponse;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

@Component
public class EmailMapper {
    private ModelMapper modelMapper = new ModelMapper();

    public EmailRequest fromInvoicetoEmailRequest(String text, Invoice invoice) {
        EmailRequest request = new EmailRequest();
        request.setTo(invoice.getCustomer().getAppUser().getEmail());
        String number = invoice.getNumber() < 10 ? "00" + invoice.getNumber() : invoice.getNumber() < 100 ? "0" + invoice.getNumber() : invoice.getNumber() + "";
        request.setSubject("Energyservices - " + text + " #" + number);
        request.setBody(text + " #" + number + "\nAmount: " + invoice.getAmount() + "\nDate: " + invoice.getDate() + "\nStatus: " + invoice.getStatus().getName());
        return request;
    }

    public EmailRequest fromAppUserToEmailRequest(String text, AppUser user) {
        EmailRequest request = new EmailRequest();
        request.setTo(user.getEmail());
        request.setSubject("Energyservices - " + text);
        request.setBody(text + "\n" + "Username: " + user.getUsername() + "\nUser first name: " + user.getName() + "\nUser last name: " + user.getSurname());
        return request;
    }
}
