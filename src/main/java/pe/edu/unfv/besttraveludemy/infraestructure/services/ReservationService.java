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
import pe.edu.unfv.besttraveludemy.api.models.request.ReservationRequest;
import pe.edu.unfv.besttraveludemy.api.models.response.HotelResponse;
import pe.edu.unfv.besttraveludemy.api.models.response.ReservationResponse;
import pe.edu.unfv.besttraveludemy.domain.entities.ReservationEntity;
import pe.edu.unfv.besttraveludemy.domain.repositories.CustomerRepository;
import pe.edu.unfv.besttraveludemy.domain.repositories.HotelRepository;
import pe.edu.unfv.besttraveludemy.domain.repositories.ReservationRepository;
import pe.edu.unfv.besttraveludemy.infraestructure.abastract_services.IReservationService;
import pe.edu.unfv.besttraveludemy.infraestructure.helper.CustomerHelper;

@Transactional
@Service
@Slf4j
@AllArgsConstructor
public class ReservationService implements IReservationService {

    private final ReservationRepository reservationRepository;
    private final CustomerRepository customerRepository;
    private final HotelRepository hotelRepository;
    private final CustomerHelper customerHelper;

    @Override
    public ReservationResponse create(ReservationRequest request) {

        var hotel = this.hotelRepository.findById(request.getIdHotel()).orElseThrow();
        var customer = this.customerRepository.findById(request.getIdClient()).orElseThrow();

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
        
        log.info("Reservation saved with id: {}", reservationPersisted.getId());

        return this.entityToResponse(reservationPersisted);
    }

    @Override
    public ReservationResponse read(UUID uuid) {
        
    	var reservationFromDB = this.reservationRepository.findById(uuid).orElseThrow();
    	return this.entityToResponse(reservationFromDB);
    }

    @Override
    public ReservationResponse update(ReservationRequest request, UUID uuid) {
    	 var hotel = this.hotelRepository.findById(request.getIdHotel()).orElseThrow();         

         var reservationToUpdate = this.reservationRepository.findById(uuid).orElseThrow();
         
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
    	var reservationToDelete = reservationRepository.findById(uuid).orElseThrow();
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
    
    public BigDecimal findPrice(Long hotelId) {
    	var hotel = this.hotelRepository.findById(hotelId).orElseThrow();
    	return hotel.getPrice().add(hotel.getPrice().multiply(charges_price_percentage));
    }

    public static final BigDecimal charges_price_percentage = BigDecimal.valueOf(0.20);
}
