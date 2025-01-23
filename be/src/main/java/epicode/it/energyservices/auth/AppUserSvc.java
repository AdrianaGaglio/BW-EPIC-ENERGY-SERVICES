package epicode.it.energyservices.auth;

import epicode.it.energyservices.auth.dto.*;
import epicode.it.energyservices.auth.jwt.JwtTokenUtil;
import epicode.it.energyservices.entities.sys_user.customer.CustomerSvc;
import epicode.it.energyservices.entities.sys_user.employee.Employee;
import epicode.it.energyservices.entities.sys_user.employee.EmployeeSvc;
import epicode.it.energyservices.exceptions.AlreadyExistsException;
import epicode.it.energyservices.exceptions.EmailAlreadyUsedException;
import epicode.it.energyservices.utils.Utils;
import epicode.it.energyservices.utils.email.EmailMapper;
import epicode.it.energyservices.utils.email.EmailSvc;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
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
    private final CustomerSvc customerSvc;
    private final EmployeeSvc employeeSvc;
    private final EmailSvc emailSvc;
    private final EmailMapper emailMapper;

    boolean existByUsername(String username) {
        return appUserRepo.existsByUsername(username.toLowerCase());
    }

    boolean existByEmail(String email) {
        return appUserRepo.existsByEmail(email.toLowerCase());
    }

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
        appUser.setRoles(registerRequest.getCustomer() != null ? Set.of(Role.CUSTOMER) : Set.of(Role.USER));

        boolean hasImage = registerRequest.getAvatar() != null && !registerRequest.getAvatar().isEmpty();
        if (hasImage) {
            appUser.setAvatar(registerRequest.getAvatar());
        } else {
            appUser.setAvatar(Utils.getAvatar(registerRequest));
        }
        if (registerRequest.getCustomer() != null) {
            appUser.setSysUser(customerSvc.create(appUser, registerRequest.getCustomer()));
        } else {
            Employee employee = new Employee();
            employee.setAppUser(appUser);
            appUser.setSysUser(employee);
            employeeSvc.save(employee);
        }

        appUserRepo.save(appUser);

        emailSvc.sendEmailHtml(emailMapper.fromAppUserToEmailRequest("New account created", appUser));

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
        appUser.setRoles(Set.of(Role.ADMIN));
        appUserRepo.save(appUser);

        return "Admin registrato con successo";
    }

    public AuthResponse Login(@Valid LoginRequest loginRequest) {
        {
            try {
                Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(loginRequest.getIdentifier(), loginRequest.getPassword())
                );

                UserDetails userDetails = (UserDetails) authentication.getPrincipal();
                return new AuthResponse(jwtTokenUtil.generateToken(userDetails));
            } catch (AuthenticationException e) {
                throw new SecurityException("Credenziali non valide", e);
            }
        }


    }


    public String sendEmailForChangePassword(@Valid EmailForPasswordResetRequest request) {
        AppUser appUser = appUserRepo.findByEmail(request.getEmail()).orElseThrow(() -> new EntityNotFoundException("Email not registered"));
        String token = jwtTokenUtil.generateTokenResetPassword(appUser);

        String resetUrl = "http://localhost:8080/api/auth/reset-password?token=" + token;

        emailSvc.sendEmailHtml(emailMapper.fromResetPasswordBodyToEmailRequest(resetUrl, appUser));

        return "Email sent successfully" + token;
    }

    public String verifyTokenPasswordReset(String token, HttpServletResponse response) {
        if (jwtTokenUtil.isTokenExpired(token)) {
            //Da implementare quando e se avremo la pagina di errore
//            try {
//                response.sendRedirect("http://localhost:4200/error?message=Token non valido o scaduto");
//            } catch (IOException e) {
//                throw new EmailSendErrorException(e.getMessage());
//            }
            return "Token not valid or expired";
        } else {

//            try {
//                response.sendRedirect("http://localhost:4200/reset-password?token=" + token);
//            } catch (IOException e) {
//                throw new EmailSendErrorException(e.getMessage());
//            }

            return token;
        }
    }

    public String resetPassword(@Valid PasswordResetRequest resetPasswordRequest) {
        if (jwtTokenUtil.isTokenExpired(resetPasswordRequest.getToken())) {
            throw new SecurityException("Token not valid or expired");
        } else {
            String username = jwtTokenUtil.getUsernameFromToken(resetPasswordRequest.getToken());
            AppUser appUser = appUserRepo.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("User not found"));
            appUser.setPassword(passwordEncoder.encode(resetPasswordRequest.getPassword()));
            appUserRepo.save(appUser);
            emailSvc.sendEmailHtml(emailMapper.fromResetPasswordSuccessBodyToEmailRequest(appUser));
            return "Password changed successfully";
        }
    }

}
