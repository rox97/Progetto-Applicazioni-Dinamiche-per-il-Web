package it.univr.webapp.repositories;

import it.univr.webapp.models.AgentsEntity;
import it.univr.webapp.models.CustomerEntity;
import it.univr.webapp.models.OrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository <OrdersEntity,Integer> {
    List<OrdersEntity> findByAgent(AgentsEntity agent);
    List<OrdersEntity> findByCustomer(CustomerEntity customer);
    int deleteByOrdNum(int ordNum);
}
