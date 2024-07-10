package pe.edu.unfv.besttraveludemy.api.controllers;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
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
	public ResponseEntity<TicketResponse> post(@Valid @RequestBody TicketRequest request){
		
		return ResponseEntity.ok(ticketService.create(request));
	}
	
	@PutMapping(path = "{id}")
	public ResponseEntity<TicketResponse> put(
			@Valid
			@PathVariable UUID id,
			@RequestBody TicketRequest request){
	
		return ResponseEntity.ok(this.ticketService.update(request, id));
	}
	
	@DeleteMapping(path = "{id}")
	public ResponseEntity<Void> delete(@PathVariable UUID id){
		this.ticketService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping
	public ResponseEntity<Map<String, BigDecimal>> getFlyPrice(@RequestParam Long flyId){
		return ResponseEntity.ok(Collections.singletonMap("flyPrice", this.ticketService.findPrice(flyId)));
	}
}
