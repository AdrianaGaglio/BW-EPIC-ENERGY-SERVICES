package epicode.it.energyservices.auth;


import epicode.it.energyservices.auth.dto.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Order(1)
public class AuthRunner implements ApplicationRunner {
    private final AppUserSvc appUserSvc;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        RegisterRequest admin = new RegisterRequest();
        admin.setName("Admin");
        admin.setSurname("Admin");
        admin.setUsername("admin");
        admin.setEmail("admin@admin.com");
        admin.setPassword("adminpwd");

        appUserSvc.registerAdmin(admin);

    }
}