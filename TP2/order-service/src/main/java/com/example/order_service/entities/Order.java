package com.example.order_service.entities;

import com.example.order_service.enums.OrderStatus;
import com.example.order_service.model.Customer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Table(name = "orders")
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date createdAt;
    private OrderStatus status;
    private Long customerId;
    @Transient
    private Customer customer;
    @OneToMany(mappedBy = "order")
    private List<ProductItem> productItems;
    public double getTotal(){
        double somme = 0;
        for (ProductItem pi:productItems){
            somme+=pi.getAmount();
        }
        return somme;
    }
}
