package pe.edu.unfv.besttraveludemy;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import pe.edu.unfv.besttraveludemy.domain.entities.ReservationEntity;
import pe.edu.unfv.besttraveludemy.domain.entities.TicketEntity;
import pe.edu.unfv.besttraveludemy.domain.entities.TourEntity;
import pe.edu.unfv.besttraveludemy.domain.repositories.CustomerRepository;
import pe.edu.unfv.besttraveludemy.domain.repositories.FlyRepository;
import lombok.extern.slf4j.Slf4j;
//import pe.edu.unfv.besttraveludemy.domain.repositories.CustomerRepository;
//import pe.edu.unfv.besttraveludemy.domain.repositories.FlyRepository;
import pe.edu.unfv.besttraveludemy.domain.repositories.HotelRepository;
import pe.edu.unfv.besttraveludemy.domain.repositories.TourRepository;
//import pe.edu.unfv.besttraveludemy.domain.repositories.ReservationRepository;
//import pe.edu.unfv.besttraveludemy.domain.repositories.TicketRepository;
//import pe.edu.unfv.besttraveludemy.domain.repositories.TourRepository;

@SpringBootApplication
@Slf4j
public class BestTravelUdemyApplication implements CommandLineRunner {

	/*
	 * public BestTravelUdemyApplication(CustomerRepository customerRepository,
	 * FlyRepository flyRepository, HotelRepository hotelRepository,
	 * ReservationRepository reservationRepository, TicketRepository
	 * ticketRepository, TourRepository tourRepository) { this.customerRepository =
	 * customerRepository; this.flyRepository = flyRepository; this.hotelRepository
	 * = hotelRepository; this.reservationRepository = reservationRepository;
	 * this.ticketRepository = ticketRepository; this.tourRepository =
	 * tourRepository; }
	 */
	
	

	public static void main(String[] args) {
		SpringApplication.run(BestTravelUdemyApplication.class, args);
	}

public BestTravelUdemyApplication(HotelRepository hotelRepository, 
		CustomerRepository customerRepository,
		FlyRepository flyRepository,
		TourRepository tourRepository) {
		super();
		this.hotelRepository = hotelRepository;
		this.customerRepository = customerRepository;
		this.flyRepository = flyRepository;
		this.tourRepository = tourRepository;
	}

	private final CustomerRepository customerRepository;
	private final FlyRepository flyRepository;
	private final HotelRepository hotelRepository;
//	private final ReservationRepository reservationRepository;
//	private final TicketRepository ticketRepository;
	private final TourRepository tourRepository;

	@Override
	public void run(String... args) throws Exception {

		/*var fly = flyRepository.findById(15L).get();
		var hotel = hotelRepository.findById(7L).get();
		var ticket = ticketRepository.findById(UUID.fromString("12345678-1234-5678-2236-567812345678")).get();
		var reservation = reservationRepository.findById(UUID.
				fromString("22345678-1234-5678-1234-567812345678")).get();
		var customer = customerRepository.findById("VIKI771012HMCRG093").get();

		log.info(String.valueOf(fly));
		log.info(String.valueOf(hotel));
		log.info(String.valueOf(ticket));
		log.info(String.valueOf(reservation));
		log.info(String.valueOf(customer));*/

//		this.flyRepository.selectLessPrice(BigDecimal.valueOf(20)).forEach(f -> System.out.println(f));
//		System.out.println("========================================================================");
//		this.flyRepository.selectBetweenPrice(BigDecimal.valueOf(15), BigDecimal.valueOf(20)).forEach(f -> System.out.println(f));
//		System.out.println("========================================================================");
//		this.flyRepository.selectOrigindestiny("Grecia", "Mexico").forEach(f -> System.out.println(f));
//		System.out.println("========================================================================");
//		this.flyRepository.selectOrigindestiny("Grecia", "Mexico").forEach(f -> System.out.println(f));

		//var fly = flyRepository.findById(1L).get();
		//System.out.println(fly);
		//fly.getTickets().forEach(ticket -> System.out.println(ticket));

		/*var fly = flyRepository.findByTicketId(UUID.fromString("12345678-1234-5678-2236-567812345678")).get();
		System.out.println(fly);*/

		//this.hotelRepository.findByPriceLessThan(BigDecimal.valueOf(100)).forEach(f -> System.out.println(f));
		//this.hotelRepository.findByPriceIsBetween(BigDecimal.valueOf(100), BigDecimal.valueOf(200)).forEach(f -> System.out.println(f));
		this.hotelRepository.findByRatingGreaterThan(4).forEach(f -> System.out.println(f));
		System.out.println("========================================================================");
		var hotel1 = hotelRepository.findByReservationId(UUID.fromString("22345678-1234-5678-1234-567812345678"));
		System.out.println(hotel1);
		System.out.println("========================================================================");
		var customer = customerRepository.findById("GOTW771012HMRGR087").orElseThrow();
		log.info("Client name: " + customer.getFullName());
		System.out.println("========================================================================");
		var fly = flyRepository.findById(11L).orElseThrow();
		log.info("fly: " + fly.getOriginName()+"-"+ fly.getDestinyName());
		
		var hotel = hotelRepository.findById(3L).orElseThrow();
		log.info("hotel: " + hotel.getName());
		
		var tour = TourEntity.builder().customer(customer).build();
		var ticket = TicketEntity.builder()
				.id(UUID.randomUUID())
				.price(fly.getPrice().multiply(BigDecimal.TEN))
				.arrivalDate(LocalDate.now())
				.departureDate(LocalDate.now())
				.purchaseDate(LocalDate.now())
				.customer(customer)
				.tour(tour)
				.fly(fly)
				.build();
				
		var reservation = ReservationEntity.builder()
				.id(UUID.randomUUID())
				.dateTimeReservation(LocalDateTime.now())
				.dateEnd(LocalDate.now().plusDays(2))
				.dateStart(LocalDate.now().plusDays(1))
				.totalDays(5)
				.price(BigDecimal.valueOf(350))
				.hotel(hotel)
				.customer(customer)
				.tour(tour)
				.build();
		System.out.println("========================================================================");
		
		tour.addReservations(reservation);
		tour.updateReservations();
		
		tour.addTicket(ticket);
		tour.updateTickets();
		
		//this.tourRepository.save(tour);
		
		var tourSaved = this.tourRepository.save(tour);
		Thread.sleep(8000);
		this.tourRepository.deleteById(tourSaved.getId());
	}
}
