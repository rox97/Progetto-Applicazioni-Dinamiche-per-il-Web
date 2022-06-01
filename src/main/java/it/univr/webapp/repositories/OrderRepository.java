package it.univr.webapp.repositories;

import it.univr.webapp.models.OrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository <OrdersEntity,Integer> {
}
