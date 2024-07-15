package pe.edu.unfv.besttraveludemy.domain.repositories.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pe.edu.unfv.besttraveludemy.domain.entities.jpa.TicketEntity;

import java.util.UUID;

@Repository
public interface TicketRepository extends CrudRepository<TicketEntity, UUID> {
}
