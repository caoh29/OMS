package caoh29.OMS.auth_server.config;

import caoh29.OMS.auth_server.entities.Client;
import caoh29.OMS.auth_server.repositories.ClientRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import java.util.Collections;
import java.util.Optional;

@Component
//public class AuthService implements AuthenticationProvider, AuthenticationConverter {
public class UserAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        Optional<Client> clientOptional = this.clientRepository.findAll().stream()
                .filter(client -> client.getUsername().equalsIgnoreCase(username))
                .findFirst();

        if (clientOptional.isEmpty()) {
            throw new BadCredentialsException("Invalid username or password");
        }

        Client client = clientOptional.get();

        if (!this.passwordEncoder.matches(password, client.getPassword())) {
            throw new BadCredentialsException("Invalid username or password");
        }

        return new UsernamePasswordAuthenticationToken(client, null, Collections.emptyList());
    }

    @Override
    public boolean supports(Class<?> authentication) {
//        return UsernamePasswordAuthenticationToken.class.equals(authentication);
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

//    @Override
//    public Authentication convert(HttpServletRequest request) {
//        return UsernamePasswordAuthenticationToken.unauthenticated(
//                request.getParameter("username"),
//                request.getParameter("password")
//        );
//    }
}
