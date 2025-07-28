package caoh29.OMS.auth_server.repositories;

import caoh29.OMS.auth_server.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<Client, UUID> {
}
