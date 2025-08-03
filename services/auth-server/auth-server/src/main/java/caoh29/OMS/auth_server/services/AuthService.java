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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

        Client client = Client.builder()
                .username(request.getUsername())
                .password(this.passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        this.clientService.save(client);

        String token = this.jwtService.generateTokenWithUsername(client.getUsername());

        return ResponseEntity.ok(token);
    }

    public ResponseEntity<String> authenticate(AuthenticateDTO request) {
        if (request.getUsername() == null || request.getPassword() == null) {
            return ResponseEntity.badRequest().body("Username and password must not be empty");
        }

        this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        UserDetails userDetails = this.clientService.loadUserByUsername(request.getUsername());

        if (userDetails == null) {
            return ResponseEntity.status(401).body("Invalid username or password");
        }

        String token = this.jwtService.generateTokenWithUsername(userDetails.getUsername());

        return ResponseEntity.ok(token);
    }
}
