package pe.edu.unfv.besttraveludemy.infraestructure.services;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pe.edu.unfv.besttraveludemy.api.models.response.HotelResponse;
import pe.edu.unfv.besttraveludemy.domain.entities.jpa.HotelEntity;
import pe.edu.unfv.besttraveludemy.domain.repositories.jpa.HotelRepository;
import pe.edu.unfv.besttraveludemy.infraestructure.abastract_services.IHotelService;
import pe.edu.unfv.besttraveludemy.util.constants.CacheConstants;
import pe.edu.unfv.besttraveludemy.util.constants.SortType;

@Transactional(readOnly = true)
@Service
@Slf4j
@AllArgsConstructor
public class HotelService implements IHotelService{

	private final HotelRepository hotelRepository;
	
	@Override
	@Cacheable(value = CacheConstants.HOTEL_CACHE_NAME)
	public Page<HotelResponse> readAll(Integer page, Integer size, SortType sortType) {		
		PageRequest pageRequest = null;
		switch (sortType) {
		case NONE  -> pageRequest = PageRequest.of(page, size);
		case LOWER -> pageRequest = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
		case UPPER -> pageRequest = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).descending());
		}
		
		log.info("Hotel reservation for: {}", pageRequest.toString());
		
		return this.hotelRepository.findAll(pageRequest).map(this::entityToResponse);
	}

	@Override
	@Cacheable(value = CacheConstants.HOTEL_CACHE_NAME)
	public Set<HotelResponse> readLessPrice(BigDecimal price) {		
		return this.hotelRepository.findByPriceLessThan(price)
				.stream()
				.map(this::entityToResponse)
				.collect(Collectors.toSet());
	}

	@Override
	@Cacheable(value = CacheConstants.HOTEL_CACHE_NAME)
	public Set<HotelResponse> readBetweenPrice(BigDecimal min, BigDecimal max) {		
		return this.hotelRepository.findByPriceIsBetween(min, max)
				.stream()
				.map(this::entityToResponse)
				.collect(Collectors.toSet());
	}

	@Override
	@Cacheable(value = CacheConstants.HOTEL_CACHE_NAME)
	public Set<HotelResponse> readByRating(Integer rating) {		
		return this.hotelRepository.findByRatingGreaterThan(rating)
				.stream()
				.map(this::entityToResponse)
				.collect(Collectors.toSet());
	}
	
private HotelResponse entityToResponse(HotelEntity entity) {			
		var response = new HotelResponse();
		BeanUtils.copyProperties(entity, response);		
		return response;
	}

}
