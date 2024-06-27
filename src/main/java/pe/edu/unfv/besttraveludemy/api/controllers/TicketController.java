package pe.edu.unfv.besttraveludemy.api.controllers;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

	@GetMapping(path = "{id}")
	public ResponseEntity<TicketResponse> get(@PathVariable UUID id){
		return ResponseEntity.ok(ticketService.read(id));
	}

	@PostMapping
	public ResponseEntity<TicketResponse> post(@RequestBody TicketRequest request){
		
		return ResponseEntity.ok(ticketService.create(request));
	}
	
	@PutMapping(path = "{id}")
	public ResponseEntity<TicketResponse> put(
			@PathVariable UUID id,
			@RequestBody TicketRequest request){
	
		return ResponseEntity.ok(this.ticketService.update(request, id));
	}
	
	@DeleteMapping(path = "{id}")
	public ResponseEntity<Void> delete(@PathVariable UUID id){
		this.ticketService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
