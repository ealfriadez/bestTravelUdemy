package pe.edu.unfv.besttraveludemy.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;
import pe.edu.unfv.besttraveludemy.api.models.request.TicketRequest;
import pe.edu.unfv.besttraveludemy.api.models.response.TicketResponse;
import pe.edu.unfv.besttraveludemy.infraestructure.abastract_services.ITicketService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "ticket")
@AllArgsConstructor
public class TicketController {

	private final ITicketService ticketService;

	@GetMapping(path = "{id}")
	public ResponseEntity<TicketResponse> get(@PathVariable UUID id){
		return ResponseEntity.ok(ticketService.read(id));
	}

	@PostMapping
	public ResponseEntity<TicketResponse> post(@RequestBody TicketRequest request){
		
		return ResponseEntity.ok(ticketService.create(request));
	}
}
