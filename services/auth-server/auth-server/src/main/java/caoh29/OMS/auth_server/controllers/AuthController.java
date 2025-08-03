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
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(
            @RequestBody RegisterDTO request
    ) {
        return this.authService.register(request);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(
            @RequestBody AuthenticateDTO request
    ) {
        return this.authService.authenticate(request);
    }
}
