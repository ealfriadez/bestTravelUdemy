package pe.edu.unfv.besttraveludemy.domain.repositories.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pe.edu.unfv.besttraveludemy.domain.entities.jpa.TourEntity;

@Repository
public interface TourRepository extends CrudRepository<TourEntity, Long> {
}
