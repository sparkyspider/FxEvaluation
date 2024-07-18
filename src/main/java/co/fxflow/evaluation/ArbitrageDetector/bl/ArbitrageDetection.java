package co.fxflow.evaluation.ArbitrageDetector.bl;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ArbitrageDetection {

    public void cycleCurrencies(Map<String, Double>exchangeRates) {

    }

    public void scanAndRecurse(String startingCurrency, Map<String, Double> exchangeRates) {
        String[] path = {startingCurrency, null, null, null, null, null};
        scanAndRecurse(0, path, null, exchangeRates);
    }

    private void scanAndRecurse(int iteration, String[] path, Double previousValue, Map<String, Double> rateList) {
        for (Map.Entry<String, Double> rate : rateList.entrySet()) {
            if (iteration == 0) {
                // Look for tickers that start with path 0
                if (base(rate.getKey()).equals(path[0])) {
                    path[1] = quote(rate.getKey());
                    scanAndRecurse(iteration + 1, path, rate.getValue(), rateList);
                }
            } else if (iteration == 1) {
                // Look for tickers that start with path 1 and that do not end in path 0
                if (base(rate.getKey()).equals(path[1]) && !quote(rate.getKey()).equals(path[0])) {
                    path[2] = base(rate.getKey());
                    path[3] = quote(rate.getKey());
                    scanAndRecurse(iteration + 1, path, previousValue * rate.getValue(), rateList);
                }
            } else if (iteration == 2) {
                // Look for tickers that start with path[2] and end in path [0]
                if (base(rate.getKey()).equals(path[3]) && quote(rate.getKey()).equals(path[0])) {
                    path[4] = base(rate.getKey());
                    path[5] = quote(rate.getKey());
                    System.out.println(String.format("Path found: %s/%s, %s/%s, %s/%s (%f)", path[0], path[1], path[2], path[3], path[4], path[5], previousValue * rate.getValue()));
                }
            }
        }
    }

    public Set<String> getDistinctCurrencies(Map<String, Double> exchangeRates) {
        return exchangeRates.keySet().stream().map(key -> key.split("/")[0]).collect(Collectors.toSet());
    }

    private String base(String ticker) {
        return ticker.split("/")[0];
    }

    private String quote(String ticker) {
        return ticker.split("/")[1];
    }

}
