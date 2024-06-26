package pe.edu.unfv.besttraveludemy.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import pe.edu.unfv.besttraveludemy.api.models.request.TicketRequest;
import pe.edu.unfv.besttraveludemy.api.models.response.TicketResponse;
import pe.edu.unfv.besttraveludemy.infraestructure.abastract_services.ITicketService;

@RestController
@RequestMapping(path = "ticket")
@AllArgsConstructor
public class TicketController {

	private final ITicketService ticketService;
	
	@PostMapping
	public ResponseEntity<TicketResponse> post(@RequestBody TicketRequest request){
		
		return ResponseEntity.ok(ticketService.create(request));
	}
}
