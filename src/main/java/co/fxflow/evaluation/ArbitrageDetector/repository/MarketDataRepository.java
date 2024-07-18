package co.fxflow.evaluation.ArbitrageDetector.repository;

import co.fxflow.evaluation.ArbitrageDetector.entity.MarketData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MarketDataRepository extends JpaRepository<MarketData, UUID> {
}
