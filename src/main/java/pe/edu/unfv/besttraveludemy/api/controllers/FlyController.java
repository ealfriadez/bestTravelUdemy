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
import pe.edu.unfv.besttraveludemy.api.models.response.FlyResponse;
import pe.edu.unfv.besttraveludemy.infraestructure.abastract_services.IFlyService;
import pe.edu.unfv.besttraveludemy.util.anotations.Notify;
import pe.edu.unfv.besttraveludemy.util.constants.SortType;

@RestController
@RequestMapping(path = "fly")
@AllArgsConstructor
@Tag(name = "Fly")
public class FlyController {

	private final IFlyService flyService;
	
	@Operation(summary = "Return a page with flights can be sorted or not")
	@GetMapping
	@Notify(value = "GET fly")
	public ResponseEntity<Page<FlyResponse>> getAll(
			@RequestParam Integer page,
			@RequestParam Integer size,
			@RequestHeader(required = false) SortType sortType){
		if (Objects.isNull(sortType)) sortType = SortType.NONE;
		var response = this.flyService.readAll(page, size, sortType);
		return response.isEmpty()?ResponseEntity.noContent().build():ResponseEntity.ok(response);
	}
	
	@Operation(summary = "Return all flights with prices less")
	@GetMapping("less_price")
	public ResponseEntity<Set<FlyResponse>> getLessPrice(
			@RequestParam BigDecimal price){		
		var response = this.flyService.readLessPrice(price);
		return response.isEmpty()?ResponseEntity.noContent().build():ResponseEntity.ok(response);
	}
	
	@Operation(summary = "Return all flights between price min and max")
	@GetMapping("between")
	public ResponseEntity<Set<FlyResponse>> getBetweenPrice(
			@RequestParam BigDecimal min,
			@RequestParam BigDecimal max){		
		var response = this.flyService.readBetweenPrice(min, max);
		return response.isEmpty()?ResponseEntity.noContent().build():ResponseEntity.ok(response);
	}
	
	@GetMapping("origin_destiny")
	public ResponseEntity<Set<FlyResponse>> getOriginDestiny(
			@RequestParam String origin,
			@RequestParam String destiny){		
		var response = this.flyService.readByOriginDestiny(origin, destiny);
		return response.isEmpty()?ResponseEntity.noContent().build():ResponseEntity.ok(response);
	}
}
