package epicode.it.energyservices.entities.sys_user.customer;

import com.github.javafaker.Faker;
import epicode.it.energyservices.auth.AppUserSvc;
import epicode.it.energyservices.auth.dto.RegisterRequest;
import epicode.it.energyservices.entities.sys_user.customer.dto.CustomerRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Order(1)
public class CustomerRunner implements ApplicationRunner {
    private final Faker faker;
    private final AppUserSvc appUserSvc;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        for (int i = 0; i < 10; i++) {

            CustomerRequest customer = new CustomerRequest();
            customer.setDenomination(faker.company().name());
            customer.setVatCode(faker.number().digits(11));
            customer.setPec(faker.internet().emailAddress());
            customer.setPhone(faker.phoneNumber().cellPhone());
            customer.setContactPhone(faker.phoneNumber().cellPhone());
            int random = faker.random().nextInt(0, 3);
            customer.setType(Type.values()[random]);

            RegisterRequest request = new RegisterRequest();
            request.setName(faker.name().firstName());
            request.setSurname(faker.name().lastName());

            String surname = request.getSurname().toLowerCase();
            if (surname.contains(" ") || surname.contains("'")) {
                surname = surname.replace(" ", "");
                surname = surname.replace("'", "");
            }

            request.setUsername(request.getName().toLowerCase().charAt(0) + surname);
            request.setEmail(request.getName() + "." + surname + "@gmail.com");
            request.setPassword("password");

            request.setCustomer(customer);

            try {
                appUserSvc.registerUser(request);
            } catch (RuntimeException e) {
                System.out.println("===> " + request);
                System.out.println(e.getMessage());
            }
        }

    }
}
