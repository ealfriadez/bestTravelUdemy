package pe.edu.unfv.besttraveludemy.infraestructure.services;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pe.edu.unfv.besttraveludemy.domain.repositories.mongo.AppUserRepository;
import pe.edu.unfv.besttraveludemy.infraestructure.abastract_services.ModifyUserService;
import pe.edu.unfv.besttraveludemy.util.exceptions.UsernameNotFoundException;

@Service
@Slf4j
@AllArgsConstructor
public class AppUserService implements ModifyUserService{

	private final AppUserRepository appUserRepository;
	
	@Override
	public Map<String, Boolean> enabled(String username) {
		var user = this.appUserRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(COLLECTION_NAME));
		user.setEnabled(!user.isEnabled());
		var userSaved = this.appUserRepository.save(user);	
		
		log.info("AppUserDocument enabled with User: {}", userSaved.getUsername().toString());
		
		return Collections.singletonMap(userSaved.getUsername(), userSaved.isEnabled());
	}

	@Override
	public Map<String, List<String>> addRole(String username, String role) {
		var user = this.appUserRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(COLLECTION_NAME));
		user.getRole().getGrantedAuthorities().add(role);
		var userSaved = this.appUserRepository.save(user);	
		var authorities = userSaved.getRole().getGrantedAuthorities();		
		
		log.info("AppUserDocument User {} add role {}", userSaved.getUsername(), userSaved.getRole().getGrantedAuthorities().toString());
		
		return Collections.singletonMap(userSaved.getUsername(), authorities);
	}

	@Override
	public Map<String, List<String>> removeRole(String username, String role) {
		var user = this.appUserRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(COLLECTION_NAME));
		user.getRole().getGrantedAuthorities().remove(role);
		var userSaved = this.appUserRepository.save(user);	
		var authorities = userSaved.getRole().getGrantedAuthorities();		
		
		log.info("AppUserDocument User {} delete role {}", userSaved.getUsername(), userSaved.getRole().getGrantedAuthorities().toString());
		
		return Collections.singletonMap(userSaved.getUsername(), authorities);
	}

	private static final String COLLECTION_NAME = "app_user";
}
