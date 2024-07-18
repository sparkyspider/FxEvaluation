package co.fxflow.evaluation.ArbitrageDetector.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class MarketDataParam {

    public MarketDataParam() {

    }

    public MarketDataParam(String baseCurrency, String quoteCurrency, Double startingPrice, Double volatilityFactor) {
        this.baseCurrency = baseCurrency;
        this.quoteCurrency = quoteCurrency;
        this.startingPrice = startingPrice;
        this.volatilityFactor = volatilityFactor;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String baseCurrency;
    private String quoteCurrency;
    private Double startingPrice;
    private Double volatilityFactor;

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

    public Double getStartingPrice() {
        return startingPrice;
    }

    public void setStartingPrice(Double startingPrice) {
        this.startingPrice = startingPrice;
    }

    public Double getVolatilityFactor() {
        return volatilityFactor;
    }

    public void setVolatilityFactor(Double volatilityFactor) {
        this.volatilityFactor = volatilityFactor;
    }
}
