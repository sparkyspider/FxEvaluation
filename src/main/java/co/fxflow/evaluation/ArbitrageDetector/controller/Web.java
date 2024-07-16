package co.fxflow.evaluation.ArbitrageDetector.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Web {

    @GetMapping("/**")
    @ResponseBody
    public String index() {
        return "<html><body><h1>Arbitrage Detector!</h1></body></html>";
    }

}
