package ma.tanassat.comptecqrses.query.repositories;

import ma.tanassat.comptecqrses.query.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,String > {
}
