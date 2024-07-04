package pe.edu.unfv.besttraveludemy.infraestructure.abastract_services;

import java.util.UUID;

import pe.edu.unfv.besttraveludemy.api.models.request.TourRequest;
import pe.edu.unfv.besttraveludemy.api.models.response.TourResponse;

public interface ITourService extends SimpleCrudService<TourRequest, TourResponse, Long>{

	void deleteTicket(Long tourId, UUID ticketId);
	UUID addTicket(Long flyId, Long tourId);
	void removeReservation(Long tourId, UUID reservationId);
	UUID addReservation(Long reservationId, Long tourId);
}
