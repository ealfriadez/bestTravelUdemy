package pe.edu.unfv.besttraveludemy.infraestructure.abastract_services;

import java.math.BigDecimal;
import java.util.Set;

import org.springframework.data.domain.Page;

import pe.edu.unfv.besttraveludemy.util.SortType;

public interface CatalogoService<R> {

	Page<R> readAll(Integer page, Integer size, SortType sortType);
	
	Set<R> readLessPrice(BigDecimal price);
	
	Set<R> readBetweenPrice(BigDecimal min, BigDecimal max);
	
	String FIELD_BY_SORT = "price";
}
