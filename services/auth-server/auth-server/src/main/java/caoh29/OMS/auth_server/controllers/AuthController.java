package caoh29.OMS.auth_server.controllers;

import caoh29.OMS.auth_server.dto.AuthenticateDTO;
import caoh29.OMS.auth_server.dto.RegisterDTO;
import caoh29.OMS.auth_server.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(
            @RequestBody RegisterDTO clientRegisterDTO
    ) {
        return this.authService.register(clientRegisterDTO);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(
            @RequestBody AuthenticateDTO clientAuthenticateDTO
    ) {
        return this.authService.authenticate(clientAuthenticateDTO);
    }
}
