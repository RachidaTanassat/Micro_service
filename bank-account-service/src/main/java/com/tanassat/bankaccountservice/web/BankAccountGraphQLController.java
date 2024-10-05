package com.tanassat.bankaccountservice.web;

import com.tanassat.bankaccountservice.dto.BankAccountRequestDTO;
import com.tanassat.bankaccountservice.dto.BankAccountResponseDTO;
import com.tanassat.bankaccountservice.entities.BankAccount;
import com.tanassat.bankaccountservice.entities.Customer;
import com.tanassat.bankaccountservice.repositories.BankAccountRepository;
import com.tanassat.bankaccountservice.repositories.CustomerRepository;
import com.tanassat.bankaccountservice.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BankAccountGraphQLController {
    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Autowired
    private AccountService accountService;

    @Autowired
    private CustomerRepository customerRepository;
    @QueryMapping
    public List<BankAccount> accountsList(){
        return bankAccountRepository.findAll();

    }

    @QueryMapping
    public BankAccount bankAccountById(@Argument String id){
        return bankAccountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Account %s not found", id)));

    }

    @MutationMapping
    public BankAccountResponseDTO addAccount(@Argument BankAccountRequestDTO bankAccount){
        return accountService.addAccount(bankAccount);
    }

    @MutationMapping
    public BankAccountResponseDTO updateAccount(@Argument String id, @Argument BankAccountRequestDTO bankAccount){
        return accountService.updateAccount(id, bankAccount);
    }

    @MutationMapping
    public boolean deleteAccount(@Argument String id){
       bankAccountRepository.deleteById(id);
       return true;
    }

    @QueryMapping
    public List<Customer> customers(){
        return customerRepository.findAll();

    }
}



