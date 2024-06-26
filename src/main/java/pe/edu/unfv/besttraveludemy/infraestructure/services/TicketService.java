package pe.edu.unfv.besttraveludemy.infraestructure.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pe.edu.unfv.besttraveludemy.api.models.request.TicketRequest;
import pe.edu.unfv.besttraveludemy.api.models.response.FlyResponse;
import pe.edu.unfv.besttraveludemy.api.models.response.TicketResponse;
import pe.edu.unfv.besttraveludemy.domain.entities.TicketEntity;
import pe.edu.unfv.besttraveludemy.domain.repositories.CustomerRepository;
import pe.edu.unfv.besttraveludemy.domain.repositories.FlyRepository;
import pe.edu.unfv.besttraveludemy.domain.repositories.TicketRepository;
import pe.edu.unfv.besttraveludemy.infraestructure.abastract_services.ITicketService;

@Transactional
@Service
@Slf4j
@AllArgsConstructor
public class TicketService implements ITicketService{

	private final FlyRepository flyRepository;
	private final CustomerRepository customerRepository;
	private final TicketRepository ticketRepository;	

	@Override
	public TicketResponse create(TicketRequest request) {
		
		var fly = flyRepository.findById(request.getIdFly()).orElseThrow();
		var customer = customerRepository.findById(request.getIdClient()).orElseThrow();
		
		var ticketToPersist = TicketEntity.builder()
				.id(UUID.randomUUID())
				.fly(fly)
				.customer(customer)
				.price(fly.getPrice().multiply(BigDecimal.valueOf(0.25)))
				.purchaseDate(LocalDate.now())
				.arrivalDate(LocalDateTime.now())
				.departureDate(LocalDateTime.now())
				.build();
		
		var ticketPersisted = this.ticketRepository.save(ticketToPersist);
		
		log.info("Ticket saved with od: {}", ticketPersisted.getId());
		
		return this.entityToResponse(ticketPersisted);
	}

	@Override
	public TicketResponse read(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TicketResponse update(TicketRequest request, UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(UUID id) {
		// TODO Auto-generated method stub		
	}
	
	private TicketResponse entityToResponse(TicketEntity entity) {
		
		var response = new TicketResponse();
		BeanUtils.copyProperties(entity, response);
		var flyResponse = new FlyResponse();
		BeanUtils.copyProperties(entity.getFly(), flyResponse);
		response.setFly(flyResponse);
		return response;
	}
}
