package ma.tanassat.comptecqrses.query.repositories;

import ma.tanassat.comptecqrses.query.entities.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepository extends JpaRepository<Operation, Long> {
}
