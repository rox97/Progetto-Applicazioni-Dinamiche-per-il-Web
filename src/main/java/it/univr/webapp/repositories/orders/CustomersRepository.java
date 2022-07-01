package it.univr.webapp.repositories.orders;

import it.univr.webapp.models.orders.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomersRepository extends JpaRepository<CustomerEntity, String> {

    List<CustomerEntity> findByOrderByCustCodeAsc();

}
