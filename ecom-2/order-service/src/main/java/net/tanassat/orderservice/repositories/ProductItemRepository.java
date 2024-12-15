package net.tanassat.orderservice.repositories;

import net.tanassat.orderservice.entities.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductItemRepository extends JpaRepository<ProductItem, Long > {
}
