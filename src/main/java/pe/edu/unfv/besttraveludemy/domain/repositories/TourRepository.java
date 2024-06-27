package pe.edu.unfv.besttraveludemy.domain.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pe.edu.unfv.besttraveludemy.domain.entities.TourEntity;

@Repository
public interface TourRepository extends CrudRepository<TourEntity, Long> {
}