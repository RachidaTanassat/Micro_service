package net.tanassat.orderservice.model;

import lombok.*;

@NoArgsConstructor @AllArgsConstructor @Data @Builder @ToString
public class Product {
    public String id;
    private String name;
    private double price;
    private int quantity;

}
