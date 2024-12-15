package net.tanassat.orderservice.web;

import net.tanassat.orderservice.entities.Order;
import net.tanassat.orderservice.repositories.OrderRepository;
import net.tanassat.orderservice.restclients.InventoryRestClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrdersRestControllers {
    private OrderRepository orderRepository;

    private InventoryRestClient inventoryRestClient;

    public OrdersRestControllers(OrderRepository orderRepository, InventoryRestClient inventoryRestClient) {
        this.orderRepository = orderRepository;
        this.inventoryRestClient = inventoryRestClient;
    }

    @GetMapping("/orders")
    public List<Order> findAllOrders(){
         List<Order> allOrders = orderRepository.findAll();
         allOrders.forEach(o->{
             o.getProductItems().forEach(pi ->{
                 pi.setProduct(inventoryRestClient.findProductById(pi.getProductId()));
             });
         });
         return allOrders;
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<Order> findOrderById(@PathVariable String id){
         Order order = orderRepository.findById(id).get();
         order.getProductItems().forEach(pi->{
             pi.setProduct(inventoryRestClient.findProductById(pi.getProductId()));
         });
         return ResponseEntity.ok(order);
    }
}
