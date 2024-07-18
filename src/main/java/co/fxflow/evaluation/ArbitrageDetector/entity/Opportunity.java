package co.fxflow.evaluation.ArbitrageDetector.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Opportunity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private LocalDateTime timestamp;
    private String path;
    private String baseCurrency;
    private Double startingValue;
    private Double returnValue;

    public Opportunity() {

    }

    public Opportunity(String path, String baseCurrency, Double startingValue, Double returnValue) {
        this.path = path;
        this.baseCurrency = baseCurrency;
        this.startingValue = startingValue;
        this.returnValue = returnValue;
        this.timestamp = LocalDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public Double getStartingValue() {
        return startingValue;
    }

    public void setStartingValue(Double startingValue) {
        this.startingValue = startingValue;
    }

    public Double getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(Double returnValue) {
        this.returnValue = returnValue;
    }
}

