package caoh29.OMS.auth_server.controllers;

import caoh29.OMS.auth_server.entities.Client;
import caoh29.OMS.auth_server.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping()
    public List<Client> getClients() {
        return this.clientService.findAll();
    }

}
