package pe.edu.unfv.besttraveludemy.infraestructure.abastract_services;

import pe.edu.unfv.besttraveludemy.api.models.request.ReservationRequest;
import pe.edu.unfv.besttraveludemy.api.models.response.ReservationResponse;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.UUID;

public interface IReservationService extends CrudService<ReservationRequest, ReservationResponse, UUID>{
	
	BigDecimal findPrice(Long hotelId, Currency currency);
}
