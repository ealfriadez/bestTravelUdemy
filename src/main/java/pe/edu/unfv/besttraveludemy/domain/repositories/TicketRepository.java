package pe.edu.unfv.besttraveludemy.domain.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pe.edu.unfv.besttraveludemy.domain.entities.TicketEntity;

import java.util.UUID;

@Repository
public interface TicketRepository extends CrudRepository<TicketEntity, UUID> {
}
