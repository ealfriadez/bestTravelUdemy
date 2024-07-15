package pe.edu.unfv.besttraveludemy.domain.repositories.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pe.edu.unfv.besttraveludemy.domain.entities.jpa.CustomerEntity;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerEntity, String> {
}
