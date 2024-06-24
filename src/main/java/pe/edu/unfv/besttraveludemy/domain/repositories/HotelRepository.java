package pe.edu.unfv.besttraveludemy.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.unfv.besttraveludemy.domain.entities.FlyEntity;
import pe.edu.unfv.besttraveludemy.domain.entities.HotelEntity;

import java.math.BigDecimal;
import java.util.Set;

public interface HotelRepository extends JpaRepository<HotelEntity, Long> {

    Set<HotelEntity> findByPriceLessThan(BigDecimal price);

    Set<HotelEntity> findByPriceIsBetween(BigDecimal min, BigDecimal max);

    Set<HotelEntity> findByRatingGreaterThan(Integer rating);
}
