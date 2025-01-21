package epicode.it.energyservices.auth;

import epicode.it.energyservices.auth.dto.LoginRequest;
import epicode.it.energyservices.auth.dto.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AppUserSvc appUserSvc;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) {
        return new ResponseEntity<>(appUserSvc.registerUser(registerRequest), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        return new ResponseEntity<>(appUserSvc.Login(loginRequest), HttpStatus.OK);
    }
}
