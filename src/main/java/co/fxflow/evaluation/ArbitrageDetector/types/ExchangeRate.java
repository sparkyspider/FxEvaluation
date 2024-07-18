package co.fxflow.evaluation.ArbitrageDetector.types;

public class ExchangeRate {

    String base;
    String quote;
    Double rate;

    public ExchangeRate(String base, String quote, Double rate) {
        this.base = base;
        this.quote = quote;
        this.rate = rate;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }
}


