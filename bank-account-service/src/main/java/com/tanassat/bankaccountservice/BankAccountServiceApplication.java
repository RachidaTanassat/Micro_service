package com.tanassat.bankaccountservice;

import com.tanassat.bankaccountservice.entities.BankAccount;
import com.tanassat.bankaccountservice.entities.Customer;
import com.tanassat.bankaccountservice.enums.AccountType;
import com.tanassat.bankaccountservice.repositories.BankAccountRepository;
import com.tanassat.bankaccountservice.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class BankAccountServiceApplication {

    public static void main(String[] args) {

        SpringApplication.run(BankAccountServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(BankAccountRepository bankAccountRepository, CustomerRepository customerRepository){
        return args -> {
            Stream.of("Mohmed", "Rachida", "Hanae", "Imane")
                    .forEach(c->{
                        Customer customer = Customer.builder()
                                .name(c)
                                .build();
                        customerRepository.save(customer);

                    });
            customerRepository.findAll().forEach(customer -> {
                for(int i =0; i<10; i++){
                    BankAccount bankAccount = BankAccount.builder()
                            .id(UUID.randomUUID().toString())
                            .type(Math.random()>0.5? AccountType.SAVING_ACCOUNT: AccountType.CURRENT_ACCOUNT)
                            .balance(100000 + Math.random()*90000)
                            .createdAt(new Date())
                            .currency("MAD")
                            .customer(customer)
                            .build();
                    bankAccountRepository.save(bankAccount);
                }
            });



        };
    }

}
