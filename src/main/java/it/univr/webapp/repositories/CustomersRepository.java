package it.univr.webapp.repositories;

import it.univr.webapp.models.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomersRepository extends JpaRepository<CustomerEntity, String> {
}
