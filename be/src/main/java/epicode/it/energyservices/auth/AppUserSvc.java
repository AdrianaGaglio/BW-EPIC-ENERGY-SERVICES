package epicode.it.energyservices.auth;

import epicode.it.energyservices.auth.dto.RegisterRequest;
import epicode.it.energyservices.auth.jwt.JwtTokenUtil;
import epicode.it.energyservices.exception.AlreadyExistsException;
import epicode.it.energyservices.exception.EmailAlreadyUsedException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Set;

@Service
@RequiredArgsConstructor
@Validated
public class AppUserSvc {
    private final AppUserRepo appUserRepo;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;

    @Transactional
    public String registerUser(@Valid RegisterRequest registerRequest) {
        if (appUserRepo.existsByEmail(registerRequest.getEmail())) {
            throw new EmailAlreadyUsedException("Email already used");
        }
        if (appUserRepo.existsByUsername(registerRequest.getUsername())) {
            throw new AlreadyExistsException("Username already used");
        }

        AppUser appUser = new AppUser();
        BeanUtils.copyProperties(registerRequest, appUser);
        appUser.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        appUser.setRoles(registerRequest.getCustomer() != null ? Set.of(Role.ROLE_CUSTOMER) : Set.of(Role.ROLE_USER));
        appUserRepo.save(appUser);

        if(registerRequest.getCustomer() != null) {

        }


        return "Registrazione avvenuta con successo";
    }

    public String registerAdmin(@Valid RegisterRequest registerRequest) {
        if (appUserRepo.existsByEmail(registerRequest.getEmail())) {
            throw new EmailAlreadyUsedException("Email already used");
        }
        if (appUserRepo.existsByUsername(registerRequest.getUsername())) {
            throw new AlreadyExistsException("Username already used");
        }
        AppUser appUser = new AppUser();
        BeanUtils.copyProperties(registerRequest, appUser);
        appUser.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        appUser.setRoles(Set.of(Role.ROLE_ADMIN));
        appUserRepo.save(appUser);

        return "Admin registrato con successo";

    }


}
