package caoh29.OMS.auth_server.services;

import caoh29.OMS.auth_server.entities.Client;
import caoh29.OMS.auth_server.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService implements UserDetailsService {
    @Autowired
    private ClientRepository clientRepository;

    public List<Client> findAll() {
        return this.clientRepository.findAll();
    }

    public void save(Client client) {
        this.clientRepository.save(client);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Client> clientOptional = this.clientRepository.findByUsername(username);
        if (clientOptional.isEmpty()) {
            throw new UsernameNotFoundException("Client not found with username: " + username);
        }
        return clientOptional.get();
    }
}
