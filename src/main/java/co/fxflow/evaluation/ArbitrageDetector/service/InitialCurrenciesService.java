package co.fxflow.evaluation.ArbitrageDetector.service;

import co.fxflow.evaluation.ArbitrageDetector.entity.MarketDataParam;
import co.fxflow.evaluation.ArbitrageDetector.repository.MarketDataParamRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InitialCurrenciesService {

    MarketDataParamRepository marketDataParamRepository;

    InitialCurrenciesService(MarketDataParamRepository marketDataParamRepository) {
        this.marketDataParamRepository = marketDataParamRepository;
    }

    @PostConstruct
    public void init() {

        marketDataParamRepository.saveAll((List.of(
            new MarketDataParam("GBP", "EUR", 1.19, 0.2),
            new MarketDataParam("GBP", "USD", 1.30, 0.2),
            new MarketDataParam("EUR", "GBP", 0.84, 0.2),
            new MarketDataParam("EUR", "USD", 1.09, 0.2),
            new MarketDataParam("USD", "GBP", 0.77, 0.2),
            new MarketDataParam("USD", "EUR", 0.92, 0.2)
        )));

    }

}
