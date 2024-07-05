package pe.edu.unfv.besttraveludemy.infraestructure.helper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import pe.edu.unfv.besttraveludemy.domain.entities.CustomerEntity;
import pe.edu.unfv.besttraveludemy.domain.entities.FlyEntity;
import pe.edu.unfv.besttraveludemy.domain.entities.HotelEntity;
import pe.edu.unfv.besttraveludemy.domain.entities.ReservationEntity;
import pe.edu.unfv.besttraveludemy.domain.entities.TicketEntity;
import pe.edu.unfv.besttraveludemy.domain.repositories.ReservationRepository;
import pe.edu.unfv.besttraveludemy.domain.repositories.TicketRepository;
import pe.edu.unfv.besttraveludemy.infraestructure.services.ReservationService;
import pe.edu.unfv.besttraveludemy.infraestructure.services.TicketService;
import pe.edu.unfv.besttraveludemy.util.BestTravelUtil;

@Transactional
@Component
@AllArgsConstructor
public class TourHelper {

	private final TicketRepository ticketRepository;
	private final ReservationRepository reservationRepository;
	
	public Set<TicketEntity> createTickets(Set<FlyEntity> flights, CustomerEntity customer){
		var response = new HashSet<TicketEntity>(flights.size());
		flights.forEach(fly -> {
			var ticketToPersist = TicketEntity.builder()
					.id(UUID.randomUUID())
					.fly(fly)
					.customer(customer)
					.price(fly.getPrice().add(fly.getPrice().multiply(TicketService.charger_price_percentage)))
					.purchaseDate(LocalDate.now())
					.arrivalDate(BestTravelUtil.getRandomLatter())
					.departureDate(BestTravelUtil.getRandomSoon())
					.build();
			response.add(this.ticketRepository.save(ticketToPersist));
		});
		return response;
	}
	
	public Set<ReservationEntity> createReservations(HashMap<HotelEntity, Integer> hotels, CustomerEntity customer){
		var response = new HashSet<ReservationEntity>(hotels.size());
		hotels.forEach((hotel, totalDays) -> {
			var reservationToPersist = ReservationEntity.builder()
	                .id(UUID.randomUUID())
	                .hotel(hotel)
	                .customer(customer)
	                .totalDays(totalDays)
	                .dateTimeReservation(LocalDateTime.now())
	                .dateStart(LocalDate.now())
	                .dateEnd(LocalDate.now().plusDays(totalDays))
	                .price(hotel.getPrice().add(hotel.getPrice().multiply(ReservationService.charges_price_percentage)).multiply(BigDecimal.valueOf(totalDays)))
	                .build();
			response.add(this.reservationRepository.save(reservationToPersist));
		});
		return response;
	}
	
	public TicketEntity createTicket(FlyEntity fly, CustomerEntity customer) {
		var ticketToPersist = TicketEntity.builder()
				.id(UUID.randomUUID())
				.fly(fly)
				.customer(customer)
				.price(fly.getPrice().add(fly.getPrice().multiply(TicketService.charger_price_percentage)))
				.purchaseDate(LocalDate.now())
				.arrivalDate(BestTravelUtil.getRandomLatter())
				.departureDate(BestTravelUtil.getRandomSoon())
				.build();
		return this.ticketRepository.save(ticketToPersist);
	}

	public ReservationEntity createReservation(HotelEntity hotel, CustomerEntity customer, Integer totalDays){
		var reservationToPersist = ReservationEntity.builder()
				.id(UUID.randomUUID())
				.hotel(hotel)
				.customer(customer)
				.totalDays(totalDays)
				.dateTimeReservation(LocalDateTime.now())
				.dateStart(LocalDate.now())
				.dateEnd(LocalDate.now().plusDays(totalDays))
				.price((hotel.getPrice().add(hotel.getPrice().multiply(ReservationService.charges_price_percentage))).multiply(BigDecimal.valueOf(totalDays)))
				.build();
		return this.reservationRepository.save(reservationToPersist);
	}
}
