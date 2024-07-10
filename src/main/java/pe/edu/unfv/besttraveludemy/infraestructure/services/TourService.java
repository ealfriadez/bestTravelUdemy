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
import pe.edu.unfv.besttraveludemy.infraestructure.helper.BlackListHelper;
import pe.edu.unfv.besttraveludemy.infraestructure.helper.CustomerHelper;
import pe.edu.unfv.besttraveludemy.infraestructure.helper.TourHelper;
import pe.edu.unfv.besttraveludemy.util.enums.Tables;
import pe.edu.unfv.besttraveludemy.util.exceptions.IdNotFoundException;

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
	private final CustomerHelper customerHelper;
	private final BlackListHelper blackListHelper;
	
	@Override
	public TourResponse create(TourRequest request) {
		blackListHelper.isInBlackListCustomer(request.getCustomerId());
		var customer = customerRepository.findById(request.getCustomerId()).orElseThrow(() -> new IdNotFoundException(Tables.customer.name()));
		var flights = new HashSet<FlyEntity>();
		request.getFlights().forEach(fly -> flights.add(this.flyRepository.findById(fly.getId()).orElseThrow(() -> new IdNotFoundException(Tables.fly.name()))));
		var hotels = new HashMap<HotelEntity, Integer>();
		request.getHotels().forEach(hotel -> hotels.put(this.hotelRepository.findById(hotel.getId()).orElseThrow(() -> new IdNotFoundException(Tables.hotel.name())), hotel.getTotalDays()));
		
		var tourToSave = TourEntity.builder()
				.tickets(this.tourHelper.createTickets(flights, customer))
				.reservations(this.tourHelper.createReservations(hotels, customer))
				.customer(customer)
				.build();
		
		var tourSaved = this.tourRepository.save(tourToSave);
		
		this.customerHelper.incrase(customer.getDni(), TourService.class);
		
		log.info("Tour saved with id: {}", tourSaved.getId());
		
		return TourResponse.builder()
				.reservationsIds(tourSaved.getReservations().stream().map(ReservationEntity::getId).collect(Collectors.toSet()))
				.ticketIds(tourSaved.getTickets().stream().map(TicketEntity::getId).collect(Collectors.toSet()))
				.id(tourSaved.getId())
				.build();
	}

	@Override
	public TourResponse read(Long id) {
		var tourFromDb = this.tourRepository.findById(id).orElseThrow(() -> new IdNotFoundException(Tables.tour.name()));
		return TourResponse.builder()
				.reservationsIds(tourFromDb.getReservations().stream().map(ReservationEntity::getId).collect(Collectors.toSet()))
				.ticketIds(tourFromDb.getTickets().stream().map(TicketEntity::getId).collect(Collectors.toSet()))
				.id(tourFromDb.getId())
				.build();
	}

	@Override
	public void delete(Long id) {
		var tourDelete = this.tourRepository.findById(id).orElseThrow(() -> new IdNotFoundException(Tables.tour.name()));
		this.tourRepository.delete(tourDelete);
	}

	@Override
	public void deleteTicket(Long tourId, UUID ticketId) {
		var tourUpdate = this.tourRepository.findById(tourId).orElseThrow(() -> new IdNotFoundException(Tables.tour.name()));
		tourUpdate.removeTicket(ticketId);
		this.tourRepository.save(tourUpdate);
	}

	@Override
	public UUID addTicket(Long tourId, Long flyId) {
		var tourUpdate = this.tourRepository.findById(tourId).orElseThrow(() -> new IdNotFoundException(Tables.tour.name()));
		var fly = this.flyRepository.findById(flyId).orElseThrow(() -> new IdNotFoundException(Tables.fly.name()));
		var ticket = this.tourHelper.createTicket(fly, tourUpdate.getCustomer());
		tourUpdate.addTicket(ticket);
		this.tourRepository.save(tourUpdate);		
		return ticket.getId();
	}

	@Override
	public void removeReservation(Long tourId, UUID reservationId) {
		var tourUpdate = this.tourRepository.findById(tourId).orElseThrow(() -> new IdNotFoundException(Tables.tour.name()));
		tourUpdate.removeReservation(reservationId);
		this.tourRepository.save(tourUpdate);
	}

	@Override
	public UUID addReservation(Long hotelId, Long tourId, Integer totalDays) {
		var tourUpdate = this.tourRepository.findById(tourId).orElseThrow(() -> new IdNotFoundException(Tables.tour.name()));
		var hotel = this.hotelRepository.findById(hotelId).orElseThrow(() -> new IdNotFoundException(Tables.hotel.name()));
		var reservation = this.tourHelper.createReservation(hotel, tourUpdate.getCustomer(), totalDays);
		tourUpdate.addReservation(reservation);
		this.tourRepository.save(tourUpdate);
		return reservation.getId();
	}
}
