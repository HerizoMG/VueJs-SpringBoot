package l2.projet.backend.repository;

import l2.projet.backend.model.Client;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client, Long> {
    Iterable<Client> findAll(Sort sort);

    @Query("SELECT c FROM Client c WHERE c.nom LIKE %?1%")
    Iterable<Client> findByNom(String nom);
}
