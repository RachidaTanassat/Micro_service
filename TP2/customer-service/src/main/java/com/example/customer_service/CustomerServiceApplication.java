package com.example.customer_service;

import com.example.customer_service.entities.Customer;
import com.example.customer_service.repo.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(CustomerRepository customerRepository){
		return args -> {
			customerRepository.saveAll(List.of(
					Customer.builder().name("moh").email("moh@gmail.com").build(),
					Customer.builder().name("rachida").email("rachida@gmail.com").build(),
					Customer.builder().name("fadma").email("fadma@gmail.com").build(),
					Customer.builder().name("soukaina").email("soukaina@gmail.com").build(),
					Customer.builder().name("fatiha").email("fatiha@gmail.com").build()


					));
			customerRepository.findAll().forEach(System.out::println);
		};
	}

}
