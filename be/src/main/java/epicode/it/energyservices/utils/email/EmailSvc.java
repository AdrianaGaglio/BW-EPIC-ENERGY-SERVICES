package epicode.it.energyservices.utils.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service

@Validated
public class EmailSvc {

    @Autowired
    private JavaMailSender mailSender;

    public String sendEmail(@Valid EmailRequest request) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(request.getTo());
        message.setSubject(request.getSubject());
        message.setText(request.getBody());
        message.setFrom("info@epic-energy-services.it");

        mailSender.send(message);
        return "Mail successfully sent to " + request.getTo();
    }

    public String sendEmailHtml(@Valid EmailRequest request) {

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);

            //SimpleMailMessage message = new SimpleMailMessage();

            helper.setTo(request.getTo());
            helper.setSubject(request.getSubject());
            helper.setText(request.getBody(), true);
            helper.setFrom("info@epic-energy-services.it");

            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return "Mail successfully sent to " + request.getTo();
    }
}
