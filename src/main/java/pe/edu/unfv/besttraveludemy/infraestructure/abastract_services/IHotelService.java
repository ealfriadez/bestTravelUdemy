package pe.edu.unfv.besttraveludemy.infraestructure.abastract_services;

import java.util.Set;

import pe.edu.unfv.besttraveludemy.api.models.response.HotelResponse;

public interface IHotelService extends CatalogoService<HotelResponse>{

	Set<HotelResponse> readByRating(Integer rating);
}
