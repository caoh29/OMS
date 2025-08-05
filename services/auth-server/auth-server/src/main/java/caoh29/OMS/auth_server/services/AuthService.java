package caoh29.OMS.auth_server.services;

import caoh29.OMS.auth_server.dto.AuthenticateDTO;
import caoh29.OMS.auth_server.dto.RegisterDTO;
import caoh29.OMS.auth_server.entities.Client;
import caoh29.OMS.auth_server.entities.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthService {
    @Autowired
    private ClientService clientService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity<String> register(RegisterDTO request) {
        if (request.getUsername() == null || request.getPassword() == null) {
            return ResponseEntity.badRequest().body("Username and password must not be empty");
        }

        UserDetails userDetails = this.clientService.loadUserByUsername(request.getUsername());

        if (Objects.equals(userDetails.getUsername(), request.getUsername())) {
            return ResponseEntity.badRequest().body("Username already exists");
        }

        Client client = Client.builder()
                .username(request.getUsername())
                .password(this.passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .role(request.getRole())
                .build();

        this.clientService.save(client);

        String token = this.jwtService.generateTokenWithUsername(client.getUsername());

        return ResponseEntity.ok(token);
    }

    public ResponseEntity<String> authenticate(AuthenticateDTO request) {
        if (request.getUsername() == null || request.getPassword() == null) {
            return ResponseEntity.badRequest().body("Username and password must not be empty");
        }

        Authentication authentication = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );


        if (!authentication.isAuthenticated()) {
            return ResponseEntity.status(401).body("Invalid username or password");
        }

        String token = this.jwtService.generateTokenWithUsername(request.getUsername());

        return ResponseEntity.ok(token);
    }
}
