package co.fxflow.evaluation.ArbitrageDetector.repository;

import co.fxflow.evaluation.ArbitrageDetector.entity.Opportunity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface OpportunityRepository extends JpaRepository<Opportunity, UUID> {
}
