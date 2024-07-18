package co.fxflow.evaluation.ArbitrageDetector.service;

import co.fxflow.evaluation.ArbitrageDetector.bl.ArbitrageDetection;
import co.fxflow.evaluation.ArbitrageDetector.entity.MarketData;
import co.fxflow.evaluation.ArbitrageDetector.repository.MarketDataRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class ArbitrageDetectorService {

    private final MarketDataRepository marketDataRepository;

    Map<String, Double> latestRates = new HashMap<>();

    public ArbitrageDetectorService(MarketDataRepository marketDataRepository) {
        this.marketDataRepository = marketDataRepository;
    }

    /**
     * Scans historic data and puts the latest forex rates into a map. Right now this is called by a controller. To use
     * this feature, allow the MarketGeneratorService a few seconds to run, then call /historic which will trigger an
     * action in the Web controller that in turn will call this function.
     */
    public void scanHistoricData() {

        /* @TODO I am aware that we need to set a window in here */

        List<MarketData> marketData = marketDataRepository.findAll(Sort.by(Sort.Direction.ASC, "timestamp"));
        marketData.forEach(forexRate -> {
            latestRates.put(forexRate.getTicker(), forexRate.getForexRate());
        });
    }



    // Create a recursive function that scans through lastRates and finds a circular path back to the starting point, summing up all the values to see if it is profitable

    public void detectArbitrage(String startingCurrency, Map<String, Double> exchangeRates) {

        new ArbitrageDetection().cycleCurrencies(exchangeRates);

        // Go to a function that searches through an array. Pass it the starting currency

        // -> Inner Function
        // Go through the array, looking for tickers that start with from[0]
        // Take note of the exchange rate 1 GBP = 1.5 USD.
        // Pass it through

        // -> Inner Function
        // Go through the array, looking for tickers that start with to[0]
        // and that do not end in from [0]
        // Remember the 1.5 USD from before multiply it by the 0.7 EUR
        // Take note of the exchange rate 1.5 USD = 0.7 EUR
        // Passed through value multipled by exchange rate.

        // -> Inner Function
        // Go through the array, looking for tickers that start with to[1]
        // and that end in from[0]
        // Take note of the exchange rate
        // Passed through value multiplied by exchange rate.

        // If the opportunity > 0 log it in the table



    }

}
