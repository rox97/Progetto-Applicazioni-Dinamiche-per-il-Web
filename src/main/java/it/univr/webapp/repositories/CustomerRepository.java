package it.univr.webapp.repositories;

import it.univr.webapp.models.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, String> {
}
