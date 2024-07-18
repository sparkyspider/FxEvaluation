package co.fxflow.evaluation.ArbitrageDetector;

import co.fxflow.evaluation.ArbitrageDetector.bl.ArbitrageDetection;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ArbitrageDetectorApplicationTests {

	private Map<String, Double> rates;

	@BeforeEach
	void setUp() {
		rates = new HashMap<String, Double>() {{
			put("GBP/EUR", 1.19);
			put("GBP/USD", 1.30);
			put("EUR/GBP", 0.84);
			put("EUR/USD", 1.09);
			put("USD/GBP", 0.77);
			put("USD/EUR", 0.91);
		}};
	}

	@Test
	void getDistinctCurrencies() {
		String result = new ArbitrageDetection().getDistinctCurrencies(rates).toString();
		assertTrue(result.contains("GBP"));
		assertTrue(result.contains("EUR"));
		assertTrue(result.contains("USD"));
	}

	@Test
	public void testRecursiveDetector() {
		new ArbitrageDetection().scanAndRecurse("GBP", rates);
	}



}
