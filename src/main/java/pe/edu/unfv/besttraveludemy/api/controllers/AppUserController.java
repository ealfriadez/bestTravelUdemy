package pe.edu.unfv.besttraveludemy.api.controllers;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import pe.edu.unfv.besttraveludemy.infraestructure.abastract_services.ModifyUserService;

@RestController
@RequestMapping(path = "user")
@AllArgsConstructor
@Tag(name = "User")
public class AppUserController {

	private final ModifyUserService modifyUserService;
	
	@Operation(summary = "Enabled or disabled user")
	@PatchMapping(path = "enabled-or-disabled")
	public ResponseEntity<Map<String, Boolean>> enabledOrDisabled(@RequestParam String username){
		return ResponseEntity.ok(this.modifyUserService.enabled(username));
	}
}
