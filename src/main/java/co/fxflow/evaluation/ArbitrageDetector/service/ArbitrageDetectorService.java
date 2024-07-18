package co.fxflow.evaluation.ArbitrageDetector.service;

import co.fxflow.evaluation.ArbitrageDetector.bl.ArbitrageDetection;
import co.fxflow.evaluation.ArbitrageDetector.entity.MarketData;
import co.fxflow.evaluation.ArbitrageDetector.entity.Opportunity;
import co.fxflow.evaluation.ArbitrageDetector.repository.MarketDataRepository;
import co.fxflow.evaluation.ArbitrageDetector.repository.OpportunityRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


@Service
public class ArbitrageDetectorService {

    private final MarketDataRepository marketDataRepository;
    private final OpportunityRepository opportunityRepository;

    Map<String, Double> latestRates = new HashMap<>();

    public ArbitrageDetectorService(MarketDataRepository marketDataRepository, OpportunityRepository opportunityRepository) {
        this.marketDataRepository = marketDataRepository;
        this.opportunityRepository = opportunityRepository;
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
            detectArbitrage("USD", latestRates);
        });



    }

    // Create a recursive function that scans through lastRates and finds a circular path back to the starting point, summing up all the values to see if it is profitable

    public void detectArbitrage(String startingCurrency, Map<String, Double> exchangeRates) {

        // I have found in my limited experimentation that starting the triangles from different corners doesn't affect
        // the result. In my limited understanding, this makes sense to me as 1 * 2 * 3 is the same as 3 * 2 * 1\

        //Map<String, Double> pathsAndGains = new ArbitrageDetection().scanAndRecurse(startingCurrency, exchangeRates);

        Map<String, Double> pathsAndGains = new ArbitrageDetection().scanAndRecurse(startingCurrency, exchangeRates);
        List<Opportunity> opportunities = new LinkedList<>();
        pathsAndGains.forEach((path, gain) -> {
            if (gain > 1) {
                opportunities.add(new Opportunity(path, gain));
            }
        });
        opportunityRepository.saveAllAndFlush(opportunities);

    }

}
