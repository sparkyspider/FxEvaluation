package co.fxflow.evaluation.ArbitrageDetector.bl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ArbitrageDetection {

    public Map<String, Double> cycleCurrencies(Map<String, Double>exchangeRates) {
        Map<String, Double> allResults = new HashMap<>();
        getDistinctCurrencies(exchangeRates).forEach(currency -> {
            allResults.putAll(scanAndRecurse(currency, exchangeRates));
        });
        return allResults;
    }

    /**
     * Scans and recursively searches for ticker triangles in the given exchange rates. Start the search using this
     * function. This function initialises the search. The overloaded sister function performs the recursion.
     *
     * @param  startingCurrency   the currency to start the search from
     * @param  exchangeRates     a map of exchange rates, where the key is the ticker and the value is the exchange rate
     */
    public Map<String, Double> scanAndRecurse(String startingCurrency, Map<String, Double> exchangeRates) {
        // @TODO: Make a table to store the results
        String[] path = {startingCurrency, null, null, null, null, null};
        return scanAndRecurse(0, path, null, exchangeRates, new HashMap<String, Double>());
    }

    //@TODO: Encapsulate the Map into a more user readable class. Create a custom ExchangeRate entry.

    /**
     * Scans and recursively searches for ticker triangles in the given exchange rates.
     *
     * @param  iteration     the current iteration level
     * @param  path          an array representing the path elements
     * @param  previousValue the previous calculated value
     * @param  rateList      the map of exchange rates
     */
    private Map<String, Double> scanAndRecurse(int iteration, String[] path, Double previousValue, Map<String, Double> rateList, Map<String, Double> result) {
        for (Map.Entry<String, Double> rate : rateList.entrySet()) {
            if (iteration == 0) {
                // Look for tickers that start with path[0]
                if (base(rate.getKey()).equals(path[0])) {
                    path[1] = quote(rate.getKey());
                    scanAndRecurse(iteration + 1, path, rate.getValue(), rateList, result);
                }
            } else if (iteration == 1) {
                // Look for tickers that start with path[1] and that do not end in path[0]
                if (base(rate.getKey()).equals(path[1]) && !quote(rate.getKey()).equals(path[0])) {
                    path[2] = base(rate.getKey());
                    path[3] = quote(rate.getKey());
                    scanAndRecurse(iteration + 1, path, previousValue * rate.getValue(), rateList, result);
                }
            } else if (iteration == 2) {
                // Look for tickers that start with path[2] and end in path[0]
                if (base(rate.getKey()).equals(path[3]) && quote(rate.getKey()).equals(path[0])) {
                    path[4] = base(rate.getKey());
                    path[5] = quote(rate.getKey());
                    String resultString = String.format("%s/%s, %s/%s, %s/%s", path[0], path[1], path[2], path[3], path[4], path[5]);
                    Double gainFactor = truncateToFourDecimals(previousValue * rate.getValue());
                    result.put(resultString, gainFactor);
                    return result;
                }
            }
        }
        return result;
    }

    public Set<String> getDistinctCurrencies(Map<String, Double> exchangeRates) {
        return exchangeRates.keySet().stream().map(key -> key.split("/")[0]).collect(Collectors.toSet());
    }

    private Double truncateToFourDecimals(Double value) {
        return (double) Math.round(value * 10000d) / 10000d;
    }

    private String base(String ticker) {
        return ticker.split("/")[0];
    }

    private String quote(String ticker) {
        return ticker.split("/")[1];
    }

}
