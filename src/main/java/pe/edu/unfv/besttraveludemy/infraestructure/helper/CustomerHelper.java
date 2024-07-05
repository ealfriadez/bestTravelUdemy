package pe.edu.unfv.besttraveludemy.infraestructure.helper;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import pe.edu.unfv.besttraveludemy.domain.repositories.CustomerRepository;

@Transactional
@Component
@AllArgsConstructor
public class CustomerHelper {

	private final CustomerRepository customerRepository;
	
	public void incrase(String customerId, Class<?> type) {
		
		var customerToUpdate = this.customerRepository.findById(customerId).orElseThrow();
		switch(type.getSimpleName()) {
			case "TourService" -> customerToUpdate.setTotalTours(customerToUpdate.getTotalTours() + 1);
			case "TicketService" -> customerToUpdate.setTotalFlights(customerToUpdate.getTotalFlights() + 1);
			case "ReservationService" -> customerToUpdate.setTotalLodgings(customerToUpdate.getTotalLodgings() + 1);
		}
		this.customerRepository.save(customerToUpdate);	
	} 
}
