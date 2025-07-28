package caoh29.OMS.auth_server.services;

import caoh29.OMS.auth_server.entities.Client;
import caoh29.OMS.auth_server.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
//public class UserService implements IUserService, AuthenticationConverter {
public class UserService implements IUserService, AuthenticationProvider {

    @Autowired
    private UserRepository userRepository;


    @Override
    public List<Client> findAll() {
        GsonJsonParser jsonParser = new GsonJsonParser();
        ArrayList<Client> clients = new ArrayList<>();
        this.userRepository.findAll().forEach(clients::add);
        return clients;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        Optional<Client> userOptional = this.userRepository.findAll().stream()
                .filter(client -> client.getUsername().equalsIgnoreCase(username))
                .findFirst();

        if (userOptional.isEmpty()) {
            return null;
        }

        Client client = userOptional.get();

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
