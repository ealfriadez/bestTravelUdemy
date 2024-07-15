package pe.edu.unfv.besttraveludemy.infraestructure.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pe.edu.unfv.besttraveludemy.api.models.request.TicketRequest;
import pe.edu.unfv.besttraveludemy.api.models.response.FlyResponse;
import pe.edu.unfv.besttraveludemy.api.models.response.TicketResponse;
import pe.edu.unfv.besttraveludemy.domain.entities.jpa.TicketEntity;
import pe.edu.unfv.besttraveludemy.domain.repositories.jpa.CustomerRepository;
import pe.edu.unfv.besttraveludemy.domain.repositories.jpa.FlyRepository;
import pe.edu.unfv.besttraveludemy.domain.repositories.jpa.TicketRepository;
import pe.edu.unfv.besttraveludemy.infraestructure.abastract_services.ITicketService;
import pe.edu.unfv.besttraveludemy.infraestructure.helper.BlackListHelper;
import pe.edu.unfv.besttraveludemy.infraestructure.helper.CustomerHelper;
import pe.edu.unfv.besttraveludemy.infraestructure.helper.EmailHelper;
import pe.edu.unfv.besttraveludemy.util.BestTravelUtil;
import pe.edu.unfv.besttraveludemy.util.enums.Tables;
import pe.edu.unfv.besttraveludemy.util.exceptions.IdNotFoundException;

@Transactional
@Service
@Slf4j
@AllArgsConstructor
public class TicketService implements ITicketService{

	private final FlyRepository flyRepository;
	private final CustomerRepository customerRepository;
	private final TicketRepository ticketRepository;
	private final CustomerHelper customerHelper;
	private final BlackListHelper blackListHelper;
	private final EmailHelper emailHelper;

	@Override
	public TicketResponse create(TicketRequest request) {
		blackListHelper.isInBlackListCustomer(request.getIdClient());
		var fly = flyRepository.findById(request.getIdFly()).orElseThrow(() -> new IdNotFoundException(Tables.fly.name()));
		var customer = customerRepository.findById(request.getIdClient()).orElseThrow(() -> new IdNotFoundException(Tables.customer.name()));
		
		var ticketToPersist = TicketEntity.builder()
				.id(UUID.randomUUID())
				.fly(fly)
				.customer(customer)
				.price(fly.getPrice().add(fly.getPrice().multiply(charger_price_percentage)))
				.purchaseDate(LocalDate.now())
				.arrivalDate(BestTravelUtil.getRandomLatter())
				.departureDate(BestTravelUtil.getRandomSoon())
				.build();
		
		var ticketPersisted = this.ticketRepository.save(ticketToPersist);
		
		this.customerHelper.incrase(customer.getDni(), TicketService.class);
		
		if (Objects.nonNull(request.getEmail())) this.emailHelper.sendMail(request.getEmail(), customer.getFullName(), Tables.ticket.name());
		
		log.info("Ticket saved with id: {}", ticketPersisted.getId());
		
		return this.entityToResponse(ticketPersisted);
	}

	@Override
	public TicketResponse read(UUID id) {

		var ticketFromDB = this.ticketRepository.findById(id).orElseThrow(() -> new IdNotFoundException(Tables.ticket.name()));
		return this.entityToResponse(ticketFromDB);
	}

	@Override
	public TicketResponse update(TicketRequest request, UUID id) {
		
		var ticketToUpdate = ticketRepository.findById(id).orElseThrow(() -> new IdNotFoundException(Tables.ticket.name()));
		var fly = flyRepository.findById(request.getIdFly()).orElseThrow(() -> new IdNotFoundException(Tables.fly.name()));
		
		ticketToUpdate.setFly(fly);
		ticketToUpdate.setPrice(fly.getPrice().add(fly.getPrice().multiply(charger_price_percentage)));
		ticketToUpdate.setDepartureDate(BestTravelUtil.getRandomSoon());
		ticketToUpdate.setArrivalDate(BestTravelUtil.getRandomLatter());
		
		var ticketUpdated = this.ticketRepository.save(ticketToUpdate);
		
		log.info("Ticket updated with id: {}", ticketUpdated.getId());
		
		return this.entityToResponse(ticketToUpdate);
	}

	@Override
	public void delete(UUID id) {

		var ticketToDelete = ticketRepository.findById(id).orElseThrow(() -> new IdNotFoundException(Tables.ticket.name()));
		this.ticketRepository.delete(ticketToDelete);
		
	}

    @Override
    public BigDecimal findPrice(Long flyId) {

        var fly = this.flyRepository.findById(flyId).orElseThrow(() -> new IdNotFoundException(Tables.fly.name()));
        return fly.getPrice().add(fly.getPrice().multiply(charger_price_percentage));
    }

	private TicketResponse entityToResponse(TicketEntity entity) {
		
		var response = new TicketResponse();
		BeanUtils.copyProperties(entity, response);
		var flyResponse = new FlyResponse();
		BeanUtils.copyProperties(entity.getFly(), flyResponse);
		response.setFly(flyResponse);
		return response;
	}

    public static final BigDecimal charger_price_percentage = BigDecimal.valueOf(0.25);
}
