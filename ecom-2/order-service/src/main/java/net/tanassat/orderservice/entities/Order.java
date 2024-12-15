package net.tanassat.orderservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;
import net.tanassat.orderservice.enums.OrderState;

import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor @AllArgsConstructor @Builder @ToString @Data
@Table(name = "orders")
public class Order {
    @Id
    private String id;
    private LocalDate date;
    private OrderState state;
    @OneToMany(mappedBy = "order")
    private List<ProductItem> productItems;
}
