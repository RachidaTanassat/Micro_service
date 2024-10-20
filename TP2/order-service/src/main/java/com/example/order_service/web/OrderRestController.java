package com.example.order_service.web;

import com.example.order_service.entities.Order;
import com.example.order_service.model.Customer;
import com.example.order_service.model.Product;
import com.example.order_service.repo.OrderRepository;
import com.example.order_service.repo.ProductItemRepository;
import com.example.order_service.services.CustomerRestClientService;
import com.example.order_service.services.InventoryRestClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderRestController {
    private OrderRepository orderRepository;
    private ProductItemRepository productItemRepository;
    private CustomerRestClientService customerRestClientService;
    private InventoryRestClientService inventoryRestClientService;

    public OrderRestController(OrderRepository orderRepository, ProductItemRepository productItemRepository, CustomerRestClientService customerRestClientService, InventoryRestClientService inventoryRestClientService) {
        this.orderRepository = orderRepository;
        this.productItemRepository = productItemRepository;
        this.customerRestClientService = customerRestClientService;
        this.inventoryRestClientService = inventoryRestClientService;
    }


    @GetMapping("/fullOrder/{id}")
    public Order getOrder(@PathVariable Long id) throws Exception {

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new Exception("Order with id " + id + " not found."));
        Customer customer = customerRestClientService.customerById(order.getCustomerId());
        order.setCustomer(customer);
        order.getProductItems().forEach(pi->{
            Product product = inventoryRestClientService.productById(pi.getProductId());
            pi.setProduct(product);
        });
        return order;
    }
}
