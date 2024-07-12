package pe.edu.unfv.besttraveludemy.infraestructure.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pe.edu.unfv.besttraveludemy.api.models.request.ReservationRequest;
import pe.edu.unfv.besttraveludemy.api.models.response.HotelResponse;
import pe.edu.unfv.besttraveludemy.api.models.response.ReservationResponse;
import pe.edu.unfv.besttraveludemy.domain.entities.ReservationEntity;
import pe.edu.unfv.besttraveludemy.domain.repositories.CustomerRepository;
import pe.edu.unfv.besttraveludemy.domain.repositories.HotelRepository;
import pe.edu.unfv.besttraveludemy.domain.repositories.ReservationRepository;
import pe.edu.unfv.besttraveludemy.infraestructure.abastract_services.IReservationService;
import pe.edu.unfv.besttraveludemy.infraestructure.helper.ApiCurrencyConnectorHelper;
import pe.edu.unfv.besttraveludemy.infraestructure.helper.BlackListHelper;
import pe.edu.unfv.besttraveludemy.infraestructure.helper.CustomerHelper;
import pe.edu.unfv.besttraveludemy.infraestructure.helper.EmailHelper;
import pe.edu.unfv.besttraveludemy.util.enums.Tables;
import pe.edu.unfv.besttraveludemy.util.exceptions.IdNotFoundException;

@Transactional
@Service
@Slf4j
@AllArgsConstructor
public class ReservationService implements IReservationService {

    private final ReservationRepository reservationRepository;
    private final CustomerRepository customerRepository;
    private final HotelRepository hotelRepository;
    private final CustomerHelper customerHelper;
    private final BlackListHelper blackListHelper;
    private final ApiCurrencyConnectorHelper currencyConnectorHelper;
    private final EmailHelper emailHelper;

    @Override
    public ReservationResponse create(ReservationRequest request) {
    	blackListHelper.isInBlackListCustomer(request.getIdClient());
        var hotel = this.hotelRepository.findById(request.getIdHotel()).orElseThrow(() -> new IdNotFoundException(Tables.hotel.name()));
        var customer = this.customerRepository.findById(request.getIdClient()).orElseThrow(() -> new IdNotFoundException(Tables.customer.name()));

        var reservationToPersist = ReservationEntity.builder()
                .id(UUID.randomUUID())
                .hotel(hotel)
                .customer(customer)
                .totalDays(request.getTotalDays())
                .dateTimeReservation(LocalDateTime.now())
                .dateStart(LocalDate.now())
                .dateEnd(LocalDate.now().plusDays(request.getTotalDays()))
                .price((hotel.getPrice().add(hotel.getPrice().multiply(charges_price_percentage))).multiply(BigDecimal.valueOf(request.getTotalDays())))
                .build();

        var reservationPersisted = reservationRepository.save(reservationToPersist);
        
        this.customerHelper.incrase(customer.getDni(), ReservationService.class);       
        
        if (Objects.nonNull(request.getEmail())) this.emailHelper.sendMail(request.getEmail(), customer.getFullName(), Tables.reservation.name());

        log.info("Reservation saved with id: {}", reservationPersisted.getId());
        
        return this.entityToResponse(reservationPersisted);
    }

    @Override
    public ReservationResponse read(UUID uuid) {
        
    	var reservationFromDB = this.reservationRepository.findById(uuid).orElseThrow(() -> new IdNotFoundException(Tables.reservation.name()));
    	return this.entityToResponse(reservationFromDB);
    }

    @Override
    public ReservationResponse update(ReservationRequest request, UUID uuid) {
    	 var hotel = this.hotelRepository.findById(request.getIdHotel()).orElseThrow(() -> new IdNotFoundException(Tables.hotel.name()));         

         var reservationToUpdate = this.reservationRepository.findById(uuid).orElseThrow(() -> new IdNotFoundException(Tables.reservation.name()));
         
         reservationToUpdate.setHotel(hotel);
         reservationToUpdate.setTotalDays(request.getTotalDays());
         reservationToUpdate.setDateTimeReservation(LocalDateTime.now());
         reservationToUpdate.setDateStart(LocalDate.now());
         reservationToUpdate.setDateEnd(LocalDate.now().plusDays(request.getTotalDays()));
         reservationToUpdate.setPrice((hotel.getPrice().add(hotel.getPrice().multiply(charges_price_percentage))).multiply(BigDecimal.valueOf(request.getTotalDays())));
         
         var reservationUpdate = this.reservationRepository.save(reservationToUpdate);
         
         log.info("Reservation update with id: {}", reservationUpdate.getId());

         return this.entityToResponse(reservationUpdate);
    }

    @Override
    public void delete(UUID uuid) {
    	var reservationToDelete = reservationRepository.findById(uuid).orElseThrow(() -> new IdNotFoundException(Tables.reservation.name()));
    	this.reservationRepository.delete(reservationToDelete);
    }

    private ReservationResponse entityToResponse(ReservationEntity entity) {

        var response = new ReservationResponse();
        BeanUtils.copyProperties(entity, response);
        var hotelResponse = new HotelResponse();
        BeanUtils.copyProperties(entity.getHotel(), hotelResponse);
        response.setHotel(hotelResponse);
        return response;
    }
    
    public BigDecimal findPrice(Long hotelId, Currency currency) {
    	var hotel = this.hotelRepository.findById(hotelId).orElseThrow(() -> new IdNotFoundException(Tables.hotel.name()));
    	var priceInDollars = hotel.getPrice().add(hotel.getPrice().multiply(charges_price_percentage));
    	if (currency.equals(Currency.getInstance("USD"))) return priceInDollars;
    	var currenyDTO = this.currencyConnectorHelper.getCurrency(currency);
    	log.info("API currency in {}, response {}", currenyDTO.getExchangeDate().toString(), currenyDTO.getRates());
    	return priceInDollars.multiply(currenyDTO.getRates().get(currency));
    }

    public static final BigDecimal charges_price_percentage = BigDecimal.valueOf(0.20);
}
