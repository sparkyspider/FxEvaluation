package co.fxflow.evaluation.ArbitrageDetector.service;

import co.fxflow.evaluation.ArbitrageDetector.entity.MarketData;
import co.fxflow.evaluation.ArbitrageDetector.entity.MarketDataParam;
import co.fxflow.evaluation.ArbitrageDetector.repository.MarketDataParamRepository;
import co.fxflow.evaluation.ArbitrageDetector.repository.MarketDataRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@EnableScheduling
public class MarketDataGeneratorService {

    final InitialCurrenciesService initialCurrenciesService;
    final MarketDataParamRepository marketDataParamRepository;
    final MarketDataRepository marketDataRepository;

    public MarketDataGeneratorService(InitialCurrenciesService initialCurrenciesService, MarketDataParamRepository marketDataParamRepository, MarketDataRepository marketDataRepository) {
        this.initialCurrenciesService = initialCurrenciesService;
        this.marketDataParamRepository = marketDataParamRepository;
        this.marketDataRepository = marketDataRepository;
    }

    List<MarketDataParam> chosenCurrencies;
    Map<String, Double> lastRates = new HashMap<>();

    @PostConstruct
    void init() {
        chosenCurrencies = marketDataParamRepository.findAll();
    }

    @Scheduled(fixedRate = 1000)
    void generateRates() {

        for (MarketDataParam marketDataParam : chosenCurrencies) {

            boolean hit = Math.floor(Math.random() * 10) == 5;
            if (hit) {

                String base = marketDataParam.getBaseCurrency();
                String quote = marketDataParam.getQuoteCurrency();
                String ticker = base.concat("/").concat(quote);
                Double volatility = marketDataParam.getVolatilityFactor();

                Double lastRate = lastRates.get(ticker);
                if (lastRate == null) {
                    lastRate = marketDataParam.getStartingPrice();
                }

                // @TODO: Make the volatility over the average, not the last value
                Double newRate = twoDecimalPlaces(lastRate + lastRate * (Math.random() * 2 - 1) * volatility);
                lastRates.put(ticker, newRate);

                marketDataRepository.save(new MarketData(marketDataParam.getBaseCurrency(), marketDataParam.getQuoteCurrency(), newRate));

            }
        }
    }

    public double twoDecimalPlaces(double d) {
        return Math.round(d * 100.0) / 100.0;
    }
}
