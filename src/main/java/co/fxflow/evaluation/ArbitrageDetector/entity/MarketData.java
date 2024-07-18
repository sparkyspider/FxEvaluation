package co.fxflow.evaluation.ArbitrageDetector.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class MarketData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String baseCurrency;
    private String quoteCurrency;
    private Double forexRate;
    private LocalDateTime timestamp;

    public MarketData() {}

    public MarketData(String baseCurrency, String quoteCurrency, Double forexRate) {
        this.baseCurrency = baseCurrency;
        this.quoteCurrency = quoteCurrency;
        this.forexRate = forexRate;
        this.timestamp = LocalDateTime.now();
    }

    public String getTicker() {
        return baseCurrency.concat("/").concat(quoteCurrency);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public String getQuoteCurrency() {
        return quoteCurrency;
    }

    public void setQuoteCurrency(String quoteCurrency) {
        this.quoteCurrency = quoteCurrency;
    }

    public Double getForexRate() {
        return forexRate;
    }

    public void setForexRate(Double forexRate) {
        this.forexRate = forexRate;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
