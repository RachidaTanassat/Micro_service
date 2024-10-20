package com.example.inventory_service;

import com.example.inventory_service.entities.Product;
import com.example.inventory_service.repo.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(ProductRepository productRepository){
		return args -> {
			for(int i =1; i<10; i++){
				productRepository.saveAll(List.of(
						Product.builder().name("computer"+i)
								.price(1200+Math.random()*1000)
								.quantity(1+i).build()
			));
			}

			productRepository.findAll().forEach(System.out::println);


		};
	}

}
