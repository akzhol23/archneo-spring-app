package iitu.labs.archneospringapp.repo;

import iitu.labs.archneospringapp.models.Cards;
import org.springframework.data.repository.CrudRepository;

public interface CardsRepository extends CrudRepository<Cards, Long> {
}
