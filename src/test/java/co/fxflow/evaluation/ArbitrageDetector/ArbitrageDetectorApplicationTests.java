package co.fxflow.evaluation.ArbitrageDetector;

import co.fxflow.evaluation.ArbitrageDetector.bl.ArbitrageDetection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
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
		rates = new HashMap<>() {{
			put("GBP/EUR", 1.19);
			put("GBP/USD", 1.30);
			put("GBP/ZAR", 23.64);
			put("EUR/GBP", 0.84);
			put("EUR/USD", 1.09);
			put("EUR/ZAR", 19.90);
			put("USD/GBP", 0.77);
			put("USD/EUR", 0.91);
			put("USD/ZAR", 18.24);
			put("ZAR/GBP", 0.042);
			put("ZAR/EUR", 0.050);
			put("ZAR/USD", 0.055);

		}};
	}

	@Test @Order(1)
	public void getDistinctCurrencies() {
		System.out.println("Testing getDistinctCurrencies()...");
		String result = new ArbitrageDetection().getDistinctCurrencies(rates).toString();
		assertTrue(result.contains("GBP"), "GBP currency not found.");
		assertTrue(result.contains("EUR"), "EUR currency not found.");
		assertTrue(result.contains("USD"), "USD currency not found.");
		System.out.println("Passed:\n - ".concat(result.concat(" currencies detected.")));
	}

	@Test @Order(2)
	public void testRecursiveDetector() {
		System.out.println("Testing scanAndRecurse()...");
		Map<String, Double> results = new ArbitrageDetection().scanAndRecurse("GBP", rates);
        assertEquals(results.get("GBP/EUR, EUR/USD, USD/GBP"), 0.9988, "GBP/EUR, EUR/USD, USD/GBP != 0.9988");
        assertEquals(results.get("GBP/USD, USD/EUR, EUR/GBP"),0.9937, "GBP/USD, USD/EUR, EUR/GBP != 0.9937");
		System.out.println("Passed:");
		results.forEach((key, value) -> System.out.printf(" - Triangle %s gains %f\n", key, value));
	}

	@Test @Order(1)
	public void testCycledRecursiveDetector() {
		System.out.println("Testing testCycledRecursiveDetector()...");
		Map <String, Double> results = new ArbitrageDetection().cycleCurrencies(rates);
		assertEquals(results.get("EUR/USD, USD/GBP, GBP/EUR"), 0.9988, "EUR/USD, USD/GBP, GBP/EUR != 0.9988");
		assertEquals(results.get("USD/EUR, EUR/GBP, GBP/USD"), 0.9937, "USD/EUR, EUR/GBP, GBP/USD != 0.9937");
		System.out.println("Passed (only two randoms have been checked here:");
		results.forEach((key, value) -> System.out.printf(" - Triangle %s gains %f%s\n", key, value, value > 1 ? " - Arbitrage Opportunity!" : ""));
	}
}
