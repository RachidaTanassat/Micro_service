package ma.tanassat.comptecqrses.commands.aggregates;

import lombok.NoArgsConstructor;
import ma.tanassat.comptecqrses.commonapi.commands.CreateAccountCommand;
import ma.tanassat.comptecqrses.commonapi.commands.CreditAccountCommand;
import ma.tanassat.comptecqrses.commonapi.commands.DebitAccountCommand;
import ma.tanassat.comptecqrses.commonapi.enums.AccountStatus;
import ma.tanassat.comptecqrses.commonapi.events.AccountActivatedEvent;
import ma.tanassat.comptecqrses.commonapi.events.AccountCreatedEvent;
import ma.tanassat.comptecqrses.commonapi.events.AccountCreditedEvent;
import ma.tanassat.comptecqrses.commonapi.events.AccountDebitedEvent;
import ma.tanassat.comptecqrses.commonapi.exceptions.AmountNegativeException;
import ma.tanassat.comptecqrses.commonapi.exceptions.BalanceNotSufficientException;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@NoArgsConstructor
public class AccountAggregate {
    @AggregateIdentifier
    private String accountId;
    private double balance;
    private String currency;
    private AccountStatus status;


    @CommandHandler
    public AccountAggregate(CreateAccountCommand createAccountCommand){
        if (createAccountCommand.getInitialBalance()<0)
            throw  new RuntimeException("Impossible de creer un compte avec un solde negatif");

        AggregateLifecycle.apply(new AccountCreatedEvent(
                createAccountCommand.getId(),
                createAccountCommand.getInitialBalance(),
                createAccountCommand.getCurrency(),
                AccountStatus.CREATED

                ));
    }
    @EventSourcingHandler
    public void on(AccountCreatedEvent event){
        this.accountId = event.getId();
        this.balance = event.getInitialBalance();
        this.currency = event.getCurrency();
        this.status = AccountStatus.CREATED;
        AggregateLifecycle.apply(new AccountActivatedEvent(
                event.getId(),
                AccountStatus.ACTIVATED
        ));
    }


    @EventSourcingHandler
    public void on(AccountActivatedEvent event){
        this.status = event.getStatus();
    }

    @CommandHandler
    public void handle(CreditAccountCommand command) throws AmountNegativeException {
        if (command.getAmount()<0) throw new AmountNegativeException("Amount should not be negative");
        AggregateLifecycle.apply(new AccountCreditedEvent(
                command.getId(),
                command.getAmount(),
                command.getCurrency()
        ));

    }

    @EventSourcingHandler
    public void on(AccountCreditedEvent event){
        this.balance+=event.getAmount();
    }

    @CommandHandler
    public void handle(DebitAccountCommand command){
        if (command.getAmount()<0) throw new AmountNegativeException("Amount should not be negative");
        if (this.balance<command.getAmount()) throw new BalanceNotSufficientException("Balance not sufficient Exception => "+ balance);
        AggregateLifecycle.apply(new AccountDebitedEvent(
                command.getId(),
                command.getAmount(),
                command.getCurrency()
        ));

    }

    @EventSourcingHandler
    public void on(AccountDebitedEvent event){
        this.balance-=event.getAmount();
    }

}
