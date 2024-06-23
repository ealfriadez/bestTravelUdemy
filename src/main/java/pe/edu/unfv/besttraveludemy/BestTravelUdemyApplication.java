package pe.edu.unfv.besttraveludemy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pe.edu.unfv.besttraveludemy.domain.repositories.FlyRepository;
import pe.edu.unfv.besttraveludemy.domain.repositories.HotelRepository;

@SpringBootApplication
@Slf4j
public class BestTravelUdemyApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BestTravelUdemyApplication.class, args);
	}

	@Autowired
	private HotelRepository hotelRepository;

	@Autowired
	private FlyRepository flyRepository;

	@Override
	public void run(String... args) throws Exception {
		var fly = flyRepository.findById(15L).get();
		var hotel = hotelRepository.findById(7L).get();

		log.info(String.valueOf(fly));
		log.info(String.valueOf(hotel));
	}
}
