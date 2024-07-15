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
import pe.edu.unfv.besttraveludemy.api.models.response.FlyResponse;
import pe.edu.unfv.besttraveludemy.domain.entities.FlyEntity;
import pe.edu.unfv.besttraveludemy.domain.repositories.FlyRepository;
import pe.edu.unfv.besttraveludemy.infraestructure.abastract_services.IFlyService;
import pe.edu.unfv.besttraveludemy.util.CacheConstants;
import pe.edu.unfv.besttraveludemy.util.SortType;

@Transactional(readOnly = true)
@Service
@Slf4j
@AllArgsConstructor
public class FlyService implements IFlyService{

	private final FlyRepository flyRepository;
	
	@Override
	@Cacheable(value = CacheConstants.FLY_CACHE_NAME)
	public Page<FlyResponse> readAll(Integer page, Integer size, SortType sortType) {		
		PageRequest pageRequest = null;
		switch (sortType) {
		case NONE  -> pageRequest = PageRequest.of(page, size);
		case LOWER -> pageRequest = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
		case UPPER -> pageRequest = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).descending());
		}
		
		log.info("Fly ordered for: {}", pageRequest.toString());
		
		return this.flyRepository.findAll(pageRequest).map(this::entityToResponse);
	}

	@Override
	@Cacheable(value = CacheConstants.FLY_CACHE_NAME)
	public Set<FlyResponse> readLessPrice(BigDecimal price) {		
		return this.flyRepository.selectLessPrice(price)
				.stream()
				.map(this::entityToResponse)
				.collect(Collectors.toSet());
	}

	@Override
	@Cacheable(value = CacheConstants.FLY_CACHE_NAME)
	public Set<FlyResponse> readBetweenPrice(BigDecimal min, BigDecimal max) {		
		return this.flyRepository.selectBetweenPrice(min, max)
				.stream()
				.map(this::entityToResponse)
				.collect(Collectors.toSet());
	}

	@Override
	@Cacheable(value = CacheConstants.FLY_CACHE_NAME)
	public Set<FlyResponse> readByOriginDestiny(String origen, String destiny) {		
		return this.flyRepository.selectOrigindestiny(origen, destiny)
				.stream()
				.map(this::entityToResponse)
				.collect(Collectors.toSet());
	}

	private FlyResponse entityToResponse(FlyEntity entity) {
		
		var response = new FlyResponse();
		BeanUtils.copyProperties(entity, response);		
		return response;
	}
}
