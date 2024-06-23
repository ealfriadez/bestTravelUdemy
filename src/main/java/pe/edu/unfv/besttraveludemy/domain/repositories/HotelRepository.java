package pe.edu.unfv.besttraveludemy.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.unfv.besttraveludemy.domain.entities.HotelEntity;

public interface HotelRepository extends JpaRepository<HotelEntity, Long> {
}
