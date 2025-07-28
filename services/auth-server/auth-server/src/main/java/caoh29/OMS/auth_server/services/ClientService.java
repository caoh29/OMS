package caoh29.OMS.auth_server.services;

import caoh29.OMS.auth_server.entities.Client;
import caoh29.OMS.auth_server.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
//public class ClientService implements IClientService, AuthenticationConverter {
public class ClientService implements IClientService, AuthenticationProvider {
    @Autowired
    private ClientRepository clientRepository;

    @Override
    public List<Client> findAll() {
        return this.clientRepository.findAll();
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        Optional<Client> clientOptional = this.clientRepository.findAll().stream()
                .filter(client -> client.getUsername().equalsIgnoreCase(username))
                .findFirst();

        if (clientOptional.isEmpty()) {
            return null;
        }

        Client client = clientOptional.get();

        if (password.matches(client.getPassword())) {
            return UsernamePasswordAuthenticationToken.authenticated(
                client,
                client.getPassword(),
                Collections.emptyList()
            );
        }

        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.equals(authentication);
    }

//    @Override
//    public Authentication convert(HttpServletRequest request) {
//        return UsernamePasswordAuthenticationToken.unauthenticated(
//                request.getParameter("username"),
//                request.getParameter("password")
//        );
//    }
}
