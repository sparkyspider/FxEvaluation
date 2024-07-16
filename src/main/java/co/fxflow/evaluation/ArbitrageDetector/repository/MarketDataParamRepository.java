package co.fxflow.evaluation.ArbitrageDetector.repository;

import co.fxflow.evaluation.ArbitrageDetector.entity.MarketDataParam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MarketDataParamRepository extends JpaRepository<MarketDataParam, UUID> {
}
