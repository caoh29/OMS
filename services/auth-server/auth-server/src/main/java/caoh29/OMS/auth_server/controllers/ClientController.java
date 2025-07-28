package caoh29.OMS.auth_server.controllers;

import caoh29.OMS.auth_server.entities.Client;
import caoh29.OMS.auth_server.services.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClientController {
    @Autowired
    private IClientService clientService;

    @GetMapping("/clients")
    public List<Client> getClients() {
        return this.clientService.findAll();
    }

}
