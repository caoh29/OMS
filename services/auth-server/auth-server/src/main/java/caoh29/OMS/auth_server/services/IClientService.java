package caoh29.OMS.auth_server.services;

import caoh29.OMS.auth_server.entities.Client;

import java.util.List;


public interface IUserService {
    List<Client> findAll();
}
