package pe.edu.unfv.besttraveludemy.api.controllers;

import lombok.AllArgsConstructor;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.unfv.besttraveludemy.api.models.request.ReservationRequest;
import pe.edu.unfv.besttraveludemy.api.models.response.ReservationResponse;
import pe.edu.unfv.besttraveludemy.infraestructure.abastract_services.IReservationService;

@RestController
@RequestMapping(path = "reservation")
@AllArgsConstructor
public class ReservationController {

    private final IReservationService reservationService;

    @GetMapping(path = "{id}")
    public ResponseEntity<ReservationResponse> get(@PathVariable UUID id){
    	return ResponseEntity.ok(reservationService.read(id));
    }
    
    @PostMapping
    public ResponseEntity<ReservationResponse> post(@RequestBody ReservationRequest request){
        return ResponseEntity.ok(reservationService.create(request));
    }
}
