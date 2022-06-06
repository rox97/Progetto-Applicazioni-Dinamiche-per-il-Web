package it.univr.webapp.repositories;

import it.univr.webapp.models.OrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository <OrdersEntity,Integer> {
    /*@Query("SELECT u FROM User u WHERE u.status = ?1 and u.name = ?2")
    List<OrdersEntity>findByAgentCode(String agentCode);*/
}
