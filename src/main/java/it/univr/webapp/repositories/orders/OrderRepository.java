package it.univr.webapp.repositories.orders;

import it.univr.webapp.models.orders.AgentsEntity;
import it.univr.webapp.models.orders.CustomerEntity;
import it.univr.webapp.models.orders.OrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository <OrdersEntity,Integer> {
    List<OrdersEntity> findByAgent(AgentsEntity agent);
    List<OrdersEntity> findByCustomer(CustomerEntity customer);
    Integer deleteByOrdNum(int ordNum);
}
