package it.univr.webapp.models;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository <OrdersEntity,Integer> {
}
