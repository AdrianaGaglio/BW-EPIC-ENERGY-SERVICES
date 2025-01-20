package epicode.it.energyservices.auth;

import epicode.it.energyservices.auth.dto.RegisterRequest;
import epicode.it.energyservices.auth.jwt.JwtTokenUtil;
import epicode.it.energyservices.exceptions.AlreadyExistsException;
import epicode.it.energyservices.exceptions.EmailAlreadyUsedException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@RequiredArgsConstructor
@Validated
public class AppUserSvc {
    private final AppUserRepository appUserRepo;
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
        appUserRepo.save(appUser);

        if(registerRequest.getCustomer() != null) {

        }


        return "Registrazione avvenuta con successo";
    }
}
