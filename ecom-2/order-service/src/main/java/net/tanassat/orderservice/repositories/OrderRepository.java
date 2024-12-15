package net.tanassat.orderservice.repositories;

import net.tanassat.orderservice.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, String > {
}
