package pe.edu.unfv.besttraveludemy.api.controllers;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import pe.edu.unfv.besttraveludemy.api.models.response.HotelResponse;
import pe.edu.unfv.besttraveludemy.infraestructure.abastract_services.IHotelService;
import pe.edu.unfv.besttraveludemy.util.SortType;

@RestController
@RequestMapping(path = "hotel")
@AllArgsConstructor
public class HotelController {

	private final IHotelService iHotelService;
	
	@GetMapping
	public ResponseEntity<Page<HotelResponse>> getAll(
			@RequestParam Integer page,
			@RequestParam Integer size,
			@RequestHeader(required = false) SortType sortType){
		if (Objects.isNull(sortType)) sortType = SortType.NONE;
		var response = this.iHotelService.realAll(page, size, sortType);
		return response.isEmpty()?ResponseEntity.noContent().build():ResponseEntity.ok(response);
	}
	
	@GetMapping("less_price")
	public ResponseEntity<Set<HotelResponse>> getLessPrice(
			@RequestParam BigDecimal price){		
		var response = this.iHotelService.readLessPrice(price);
		return response.isEmpty()?ResponseEntity.noContent().build():ResponseEntity.ok(response);
	}
	
	@GetMapping("between")
	public ResponseEntity<Set<HotelResponse>> getBetweenPrice(
			@RequestParam BigDecimal min,
			@RequestParam BigDecimal max){		
		var response = this.iHotelService.readBetweenPrice(min, max);
		return response.isEmpty()?ResponseEntity.noContent().build():ResponseEntity.ok(response);
	}
	
	@GetMapping("rating")
	public ResponseEntity<Set<HotelResponse>> getByRating(
			@RequestParam Integer rating){	
		if (rating > 4) rating = 4;
		if (rating < 1) rating = 1;
		var response = this.iHotelService.readByRating(rating);
		return response.isEmpty()?ResponseEntity.noContent().build():ResponseEntity.ok(response);
	}
}
