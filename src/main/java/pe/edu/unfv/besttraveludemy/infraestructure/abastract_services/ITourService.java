package pe.edu.unfv.besttraveludemy.infraestructure.abastract_services;

import java.util.UUID;

import pe.edu.unfv.besttraveludemy.api.models.request.TourRequest;
import pe.edu.unfv.besttraveludemy.api.models.response.TourResponse;

public interface ITourService extends SimpleCrudService<TourRequest, TourResponse, Long>{

	void deleteTicket(UUID ticketId, Long tourId);
	UUID addTicket(Long flyId, Long tourId);
	void removeReservation(UUID reservationId, Long tourId);
	UUID addReservation(Long reservationId, Long tourId);
}
