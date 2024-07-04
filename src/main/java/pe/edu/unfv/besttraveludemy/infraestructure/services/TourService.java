package pe.edu.unfv.besttraveludemy.infraestructure.services;

import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pe.edu.unfv.besttraveludemy.api.models.request.TourRequest;
import pe.edu.unfv.besttraveludemy.api.models.response.TourResponse;
import pe.edu.unfv.besttraveludemy.domain.entities.FlyEntity;
import pe.edu.unfv.besttraveludemy.domain.entities.HotelEntity;
import pe.edu.unfv.besttraveludemy.domain.entities.ReservationEntity;
import pe.edu.unfv.besttraveludemy.domain.entities.TicketEntity;
import pe.edu.unfv.besttraveludemy.domain.entities.TourEntity;
import pe.edu.unfv.besttraveludemy.domain.repositories.CustomerRepository;
import pe.edu.unfv.besttraveludemy.domain.repositories.FlyRepository;
import pe.edu.unfv.besttraveludemy.domain.repositories.HotelRepository;
import pe.edu.unfv.besttraveludemy.domain.repositories.TourRepository;
import pe.edu.unfv.besttraveludemy.infraestructure.abastract_services.ITourService;
import pe.edu.unfv.besttraveludemy.infraestructure.helper.TourHelper;

@Transactional
@Service
@Slf4j
@AllArgsConstructor
public class TourService implements ITourService{
		
	private final TourRepository tourRepository;
	private final FlyRepository flyRepository;
	private final CustomerRepository customerRepository;
	private final HotelRepository hotelRepository;
	private final TourHelper tourHelper;
	
	@Override
	public TourResponse create(TourRequest request) {
		var customer = customerRepository.findById(request.getCustomerId()).orElseThrow();
		var flights = new HashSet<FlyEntity>();
		request.getFlights().forEach(fly -> flights.add(this.flyRepository.findById(fly.getId()).orElseThrow()));
		var hotels = new HashMap<HotelEntity, Integer>();
		request.getHotels().forEach(hotel -> hotels.put(this.hotelRepository.findById(hotel.getId()).orElseThrow(), hotel.getTotalDays()));
		
		var tourToSave = TourEntity.builder()
				.tickets(this.tourHelper.createTickets(flights, customer))
				.reservations(this.tourHelper.createReservations(hotels, customer))
				.customer(customer)
				.build();
		
		var tourSaved = this.tourRepository.save(tourToSave);
		return TourResponse.builder()
				.reservationsIds(tourSaved.getReservations().stream().map(ReservationEntity::getId).collect(Collectors.toSet()))
				.ticketIds(tourSaved.getTickets().stream().map(TicketEntity::getId).collect(Collectors.toSet()))
				.id(tourSaved.getId())
				.build();
	}

	@Override
	public TourResponse read(Long id) {
		var tourFromDb = this.tourRepository.findById(id).orElseThrow();
		return TourResponse.builder()
				.reservationsIds(tourFromDb.getReservations().stream().map(ReservationEntity::getId).collect(Collectors.toSet()))
				.ticketIds(tourFromDb.getTickets().stream().map(TicketEntity::getId).collect(Collectors.toSet()))
				.id(tourFromDb.getId())
				.build();
	}

	@Override
	public void delete(Long id) {
		var tourDelete = this.tourRepository.findById(id).orElseThrow();
		this.tourRepository.delete(tourDelete);
	}

	@Override
	public void deleteTicket(UUID ticketId, Long tourId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UUID addTicket(Long flyId, Long tourId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeReservation(UUID reservationId, Long tourId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UUID addReservation(Long reservationId, Long tourId) {
		// TODO Auto-generated method stub
		return null;
	}

}
