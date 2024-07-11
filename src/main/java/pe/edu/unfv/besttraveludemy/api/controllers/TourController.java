package pe.edu.unfv.besttraveludemy.api.controllers;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import pe.edu.unfv.besttraveludemy.api.models.request.TourRequest;
import pe.edu.unfv.besttraveludemy.api.models.response.ErrorResponse;
import pe.edu.unfv.besttraveludemy.api.models.response.TourResponse;
import pe.edu.unfv.besttraveludemy.infraestructure.abastract_services.ITourService;

@RestController
@RequestMapping(path = "tour")
@AllArgsConstructor
@Tag(name = "Tour")
public class TourController {

    private final ITourService iTourService;

    @ApiResponse(
    		responseCode = "400", 
    		description = "When the request have a field invalid we response this",
    		content = {
    				@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
    		}
    	)
    @Operation(
    			summary = "Save in system un tour based in a list of hotels and flights"
    		)
    @PostMapping
    public ResponseEntity<TourResponse> post(@Valid @RequestBody TourRequest request){
        return ResponseEntity.ok(this.iTourService.create(request));
    }

    @Operation(
			summary = "Return a tour with at Id passed"
		)
    @GetMapping(path = "{id}")
    public ResponseEntity<TourResponse> post(@PathVariable Long id){
        return ResponseEntity.ok(this.iTourService.read(id));
    }
    
    @Operation(
			summary = "Deleted a tour with at Id passed"
		)
    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
    	this.iTourService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
    @Operation(
			summary = "Deleted a ticket with at tour id passed"
		)
    @PatchMapping(path = "{tourId}/remove_ticket/{ticketId}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long tourId, @PathVariable UUID ticketId){
    	this.iTourService.deleteTicket(tourId, ticketId);
        return ResponseEntity.noContent().build();
    }
    
    @Operation(
			summary = "Add a ticket from tour"
		)
    @PatchMapping(path = "{tourId}/add_ticket/{flyId}")
    public ResponseEntity<Map<String, UUID>> postTicket(@PathVariable Long tourId, @PathVariable Long flyId){
    	var response = Collections.singletonMap("ticketId", this.iTourService.addTicket(tourId, flyId));
        return ResponseEntity.ok(response);
    }

    @Operation(
			summary = "Remove a reservation from tour"
		)
    @PatchMapping(path = "{tourId}/remove_reservation/{reservationId}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long tourId, @PathVariable UUID reservationId){
        this.iTourService.removeReservation(tourId, reservationId);
        return ResponseEntity.noContent().build();
    }

    @Operation(
			summary = "Add a reservation from tour"
		)
    @PatchMapping(path = "{tourId}/add_reservation/{hotelId}")
    public ResponseEntity<Map<String, UUID>> postTicket(@PathVariable Long tourId, @PathVariable Long hotelId, @RequestParam Integer totalDays){
        var response = Collections.singletonMap("ticketId", this.iTourService.addReservation(tourId, hotelId, totalDays));
        return ResponseEntity.ok(response);
    }
}
