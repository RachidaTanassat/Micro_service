package net.tanassat.inventoryservice.web;

import net.tanassat.inventoryservice.entities.Product;
import net.tanassat.inventoryservice.repository.ProductRepository;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductRestController {
    private ProductRepository productRepository;

    public ProductRestController(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @GetMapping("/products")
   // @PreAuthorize("hasAnyAuthority('ADMIN')")
    public List<Product> products(){
        return productRepository.findAll();
    }
    @GetMapping("/products/{id}")
    //@PreAuthorize("hasAnyAuthority('USER')")
    public Product productById(@PathVariable String id){
        return productRepository.findById(id).get() ;
    }

    @GetMapping("/auth")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public Authentication authentication(Authentication authentication){
        return authentication;
    }

}
