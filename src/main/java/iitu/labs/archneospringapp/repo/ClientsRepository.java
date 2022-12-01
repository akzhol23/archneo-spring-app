package iitu.labs.archneospringapp.repo;

import iitu.labs.archneospringapp.models.Clients;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ClientsRepository extends CrudRepository<Clients, Long>  {
    Optional<Clients> findUserByUsername(String username);
}
