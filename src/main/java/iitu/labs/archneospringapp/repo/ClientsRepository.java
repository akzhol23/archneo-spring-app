package iitu.labs.archneospringapp.repo;

import iitu.labs.archneospringapp.models.Clients;
import org.springframework.data.repository.CrudRepository;

public interface ClientsRepository extends CrudRepository<Clients, Long>  {
}
