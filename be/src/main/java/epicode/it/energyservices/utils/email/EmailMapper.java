package epicode.it.energyservices.utils.email;

import epicode.it.energyservices.auth.AppUser;
import epicode.it.energyservices.entities.invoice.Invoice;
import epicode.it.energyservices.entities.invoice.dto.InvoiceResponse;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@Component
public class EmailMapper {

    public EmailRequest fromInvoicetoEmailRequest(String text, Invoice invoice) {
        EmailRequest request = new EmailRequest();
        request.setTo(invoice.getCustomer().getAppUser().getEmail());
        String number = invoice.getNumber() < 10 ? "00" + invoice.getNumber() : invoice.getNumber() < 100 ? "0" + invoice.getNumber() : invoice.getNumber() + "";
        request.setSubject("Energyservices - " + text + " #" + number);
        request.setBody(fromInvoiceToEmailBody(text, invoice));
        return request;
    }

    public EmailRequest fromAppUserToEmailRequest(String text, AppUser user) {
        EmailRequest request = new EmailRequest();
        request.setTo(user.getEmail());
        request.setSubject("Energyservices - " + text);
        request.setBody(fromAppUserToEmailBody(text, user));
        return request;
    }

    public String fromInvoiceToEmailBody(String text, Invoice invoice) {
        String template = loadTemplate("src/main/resources/templates/invoice.html");
        Map<String, String> values = new HashMap<>();
        values.put("text", text);
        values.put("number", invoice.getNumber() < 10 ? "00" + invoice.getNumber() : invoice.getNumber() < 100 ? "0" + invoice.getNumber() : invoice.getNumber() + "");
        values.put("amount", invoice.getAmount() + "");
        values.put("date", invoice.getDate() + "");
        values.put("status", invoice.getStatus().getName());
        return processTemplate(template, values);
    }

    public String fromAppUserToEmailBody(String text, AppUser user) {
        String template = loadTemplate("src/main/resources/templates/user.html");
        Map<String, String> values = new HashMap<>();
        values.put("text", text);
        values.put("username", user.getUsername());
        values.put("email", user.getEmail());

        return processTemplate(template, values);
    }

    private String loadTemplate(String filePath)  {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String processTemplate(String template, Map<String, String> values) {
        for (Map.Entry<String, String> entry : values.entrySet()) {
            template = template.replace("{{" + entry.getKey() + "}}", entry.getValue());
        }
        return template;
    }
}
