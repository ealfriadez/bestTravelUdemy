package pe.edu.unfv.besttraveludemy.api.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.unfv.besttraveludemy.api.models.request.TourRequest;
import pe.edu.unfv.besttraveludemy.api.models.response.TourResponse;
import pe.edu.unfv.besttraveludemy.infraestructure.abastract_services.ITourService;

import java.util.UUID;

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
        return ResponseEntity.ok(this.iTourService.read(request));
    }
}
