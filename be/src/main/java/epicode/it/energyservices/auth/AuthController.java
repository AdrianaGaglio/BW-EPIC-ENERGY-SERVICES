package epicode.it.energyservices.auth;

import epicode.it.energyservices.auth.dto.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        return new ResponseEntity<>(appUserSvc.Login(loginRequest), HttpStatus.OK);
    }

    @PostMapping("/requestChangePassword")
    public ResponseEntity<String> changePassword(@RequestBody EmailForPasswordResetRequest passwordResetRequest) {
        return new ResponseEntity<>(appUserSvc.sendEmailForChangePassword(passwordResetRequest), HttpStatus.OK);
    }

    @GetMapping("/reset-password")
    public ResponseEntity<String> resetPasswordRedirect(@RequestParam String token, HttpServletResponse response) {
        return new ResponseEntity<>(appUserSvc.verifyTokenPasswordReset(token, response), HttpStatus.OK);
    }

    @PatchMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody PasswordResetRequest passwordResetRequest) {
        return new ResponseEntity<>(appUserSvc.resetPassword(passwordResetRequest), HttpStatus.OK);
    }
}
