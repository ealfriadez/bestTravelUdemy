package pe.edu.unfv.besttraveludemy.api.controllers;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;
import pe.edu.unfv.besttraveludemy.api.models.request.TourRequest;
import pe.edu.unfv.besttraveludemy.api.models.response.TourResponse;
import pe.edu.unfv.besttraveludemy.infraestructure.abastract_services.ITourService;

@RestController
@RequestMapping(path = "tour")
@AllArgsConstructor
public class TourController {

    private final ITourService iTourService;

    @PostMapping
    public ResponseEntity<TourResponse> post(@RequestBody TourRequest request){
        return ResponseEntity.ok(this.iTourService.create(request));
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<TourResponse> post(@PathVariable Long id){
        return ResponseEntity.ok(this.iTourService.read(id));
    }
    
    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
    	this.iTourService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
    @PatchMapping(path = "{tourId}/remove_ticket/{ticketId}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long tourId, @PathVariable UUID ticketId){
    	this.iTourService.deleteTicket(tourId, ticketId);
        return ResponseEntity.noContent().build();
    }
    
    @PatchMapping(path = "{tourId}/add_ticket/{flyId}")
    public ResponseEntity<Map<String, UUID>> postTicket(@PathVariable Long tourId, @PathVariable Long flyId){
    	var response = Collections.singletonMap("ticketId", this.iTourService.addTicket(tourId, flyId));
        return ResponseEntity.ok(response);
    }

    @PatchMapping(path = "{tourId}/remove_reservation/{reservationId}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long tourId, @PathVariable UUID reservationId){
        this.iTourService.removeReservation(tourId, reservationId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(path = "{tourId}/add_reservation/{hotelId}")
    public ResponseEntity<Map<String, UUID>> postTicket(@PathVariable Long tourId, @PathVariable Long hotelId, @RequestParam Integer totalDays){
        var response = Collections.singletonMap("ticketId", this.iTourService.addReservation(tourId, hotelId, totalDays));
        return ResponseEntity.ok(response);
    }
}
