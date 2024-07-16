package co.fxflow.evaluation.ArbitrageDetector.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;
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



}
