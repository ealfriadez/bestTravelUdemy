package pe.edu.unfv.besttraveludemy.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.unfv.besttraveludemy.domain.entities.FlyEntity;

@Repository
public interface FlyRepository extends JpaRepository<FlyEntity, Long> {
}
