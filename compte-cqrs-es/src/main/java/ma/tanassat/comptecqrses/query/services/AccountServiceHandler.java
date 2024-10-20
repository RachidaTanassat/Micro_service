package ma.tanassat.comptecqrses.query.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.tanassat.comptecqrses.commonapi.enums.OperationType;
import ma.tanassat.comptecqrses.commonapi.events.AccountActivatedEvent;
import ma.tanassat.comptecqrses.commonapi.events.AccountCreatedEvent;
import ma.tanassat.comptecqrses.commonapi.events.AccountCreditedEvent;
import ma.tanassat.comptecqrses.commonapi.events.AccountDebitedEvent;
import ma.tanassat.comptecqrses.commonapi.queries.GetAccountByIdQuery;
import ma.tanassat.comptecqrses.commonapi.queries.GetAllAccountsQuery;
import ma.tanassat.comptecqrses.query.entities.Account;
import ma.tanassat.comptecqrses.query.entities.Operation;
import ma.tanassat.comptecqrses.query.repositories.AccountRepository;
import ma.tanassat.comptecqrses.query.repositories.OperationRepository;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class AccountServiceHandler {
    private AccountRepository accountRepository;
    private OperationRepository operationRepository;
    @EventHandler
    public void on(AccountCreatedEvent event){
        log.info("*********************");
        log.info("Account Created Event recieived");
        Account account = new Account();
        account.setId(event.getId());
        account.setCurrency(event.getCurrency());
        account.setStatus(event.getStatus());
        account.setBalance(event.getInitialBalance());

        accountRepository.save(account);

    }

    @EventHandler
    public void on(AccountActivatedEvent event){
        log.info("*********************");
        log.info("Account Activated Event recieived");
        Account account = accountRepository.findById(event.getId()).get();
        account.setStatus(event.getStatus());
        accountRepository.save(account);

    }


    @EventHandler
    public void on(AccountDebitedEvent event){
        log.info("*********************");
        log.info("Account Debited Event recieived");
        Account account = accountRepository.findById(event.getId()).get();

        Operation operation = new Operation();
        operation.setAmount(event.getAmount());
        operation.setDate(new Date()); // a ne pas faire
        operation.setType(OperationType.CREDIT);
        operation.setAccount(account);
        operationRepository.save(operation);

        account.setBalance(account.getBalance()- event.getAmount());
        accountRepository.save(account);

    }


    @EventHandler
    public void on(AccountCreditedEvent event){
        log.info("*********************");
        log.info("Account Credited Event recieived");
        Account account = accountRepository.findById(event.getId()).get();

        Operation operation = new Operation();
        operation.setAmount(event.getAmount());
        operation.setDate(new Date()); // a ne pas faire
        operation.setType(OperationType.DEBIT);
        operation.setAccount(account);
        operationRepository.save(operation);

        account.setBalance(account.getBalance() + event.getAmount());
        accountRepository.save(account);
    }


    @QueryHandler
    public List<Account> on(GetAllAccountsQuery query){
        return accountRepository.findAll();

    }

    @QueryHandler
    public Account on(GetAccountByIdQuery query){
        return accountRepository.findById(query.getId()).get();

    }
}
