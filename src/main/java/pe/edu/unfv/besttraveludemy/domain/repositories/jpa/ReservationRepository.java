package pe.edu.unfv.besttraveludemy.domain.repositories.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pe.edu.unfv.besttraveludemy.domain.entities.jpa.ReservationEntity;

import java.util.UUID;

@Repository
public interface ReservationRepository extends CrudRepository<ReservationEntity, UUID> {
}
