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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import pe.edu.unfv.besttraveludemy.api.models.response.HotelResponse;
import pe.edu.unfv.besttraveludemy.infraestructure.abastract_services.IHotelService;
import pe.edu.unfv.besttraveludemy.util.anotations.Notify;
import pe.edu.unfv.besttraveludemy.util.constants.SortType;

@RestController
@RequestMapping(path = "hotel")
@AllArgsConstructor
@Tag(name = "Hotel")
public class HotelController {

	private final IHotelService iHotelService;
	
	@Operation(summary = "Return a page with hotel can be sorted or not")
	@GetMapping
	@Notify(value = "GET hotel")
	public ResponseEntity<Page<HotelResponse>> getAll(
			@RequestParam Integer page,
			@RequestParam Integer size,
			@RequestHeader(required = false) SortType sortType){
		if (Objects.isNull(sortType)) sortType = SortType.NONE;
		var response = this.iHotelService.readAll(page, size, sortType);
		return response.isEmpty()?ResponseEntity.noContent().build():ResponseEntity.ok(response);
	}
	
	@Operation(summary = "Return all hotels with prices less")
	@GetMapping("less_price")
	public ResponseEntity<Set<HotelResponse>> getLessPrice(
			@RequestParam BigDecimal price){		
		var response = this.iHotelService.readLessPrice(price);
		return response.isEmpty()?ResponseEntity.noContent().build():ResponseEntity.ok(response);
	}
	
	@Operation(summary = "Return all hotels between price min and max")
	@GetMapping("between")
	public ResponseEntity<Set<HotelResponse>> getBetweenPrice(
			@RequestParam BigDecimal min,
			@RequestParam BigDecimal max){		
		var response = this.iHotelService.readBetweenPrice(min, max);
		return response.isEmpty()?ResponseEntity.noContent().build():ResponseEntity.ok(response);
	}
	
	@Operation(summary = "Return all hotels by rating")
	@GetMapping("rating")
	public ResponseEntity<Set<HotelResponse>> getByRating(
			@RequestParam Integer rating){	
		if (rating > 4) rating = 4;
		if (rating < 1) rating = 1;
		var response = this.iHotelService.readByRating(rating);
		return response.isEmpty()?ResponseEntity.noContent().build():ResponseEntity.ok(response);
	}
}
