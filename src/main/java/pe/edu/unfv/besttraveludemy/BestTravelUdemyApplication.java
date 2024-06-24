package pe.edu.unfv.besttraveludemy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pe.edu.unfv.besttraveludemy.domain.repositories.*;

import java.math.BigDecimal;
import java.util.UUID;

@SpringBootApplication
@Slf4j
public class BestTravelUdemyApplication implements CommandLineRunner {

	public BestTravelUdemyApplication(CustomerRepository customerRepository, FlyRepository flyRepository,
									  HotelRepository hotelRepository, ReservationRepository reservationRepository,
									  TicketRepository ticketRepository, TourRepository tourRepository) {
		this.customerRepository = customerRepository;
		this.flyRepository = flyRepository;
		this.hotelRepository = hotelRepository;
		this.reservationRepository = reservationRepository;
		this.ticketRepository = ticketRepository;
		this.tourRepository = tourRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(BestTravelUdemyApplication.class, args);
	}

	private final CustomerRepository customerRepository;
	private final FlyRepository flyRepository;
	private final HotelRepository hotelRepository;
	private final ReservationRepository reservationRepository;
	private final TicketRepository ticketRepository;
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
	}
}
