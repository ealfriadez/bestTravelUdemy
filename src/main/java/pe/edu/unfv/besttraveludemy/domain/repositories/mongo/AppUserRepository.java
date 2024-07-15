package pe.edu.unfv.besttraveludemy.domain.repositories.mongo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import pe.edu.unfv.besttraveludemy.domain.entities.documents.AppUserDocument;


public interface AppUserRepository extends MongoRepository<AppUserDocument, String>{

	Optional<AppUserDocument> findByUsername(String username); 
}
