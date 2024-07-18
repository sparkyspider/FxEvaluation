package co.fxflow.evaluation.ArbitrageDetector.controller;

import co.fxflow.evaluation.ArbitrageDetector.service.ArbitrageDetectorService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Web {

    private final ArbitrageDetectorService arbitrageDetectorService;

    public Web(ArbitrageDetectorService arbitrageDetectorService) {
        this.arbitrageDetectorService = arbitrageDetectorService;
    }

    @GetMapping("/**")
    @ResponseBody
    public String index() {
        return "<html><body><h1>Arbitrage Detector!</h1></body></html>";
    }

    @GetMapping("/historic")
    @ResponseBody
    public String historic() {
        arbitrageDetectorService.scanHistoricData();
        return "<html><body><h1>See Data</h1></body></html>";
    }

}
