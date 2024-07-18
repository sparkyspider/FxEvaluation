package co.fxflow.evaluation.ArbitrageDetector;

import co.fxflow.evaluation.ArbitrageDetector.bl.ArbitrageDetection;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

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

	@Test @Order(1)
	void getDistinctCurrencies() {
		System.out.println("Testing getDistinctCurrencies()...");
		String result = new ArbitrageDetection().getDistinctCurrencies(rates).toString();
		assertTrue(result.contains("GBP"), "GBP currency not found.");
		assertTrue(result.contains("EUR"), "EUR currency not found.");
		assertTrue(result.contains("USD"), "USD currency not found.");
		System.out.println("Passed:\n - ".concat(result.toString().concat(" currencies detected.")));
	}

	@Test @Order(2)
	public void testRecursiveDetector() {
		System.out.println("Testing scanAndRecurse()...");
		Map<String, Double> results = new ArbitrageDetection().scanAndRecurse("GBP", rates);
		assertTrue(results.get("GBP/EUR, EUR/USD, USD/GBP").equals(0.9988), "GBP/EUR, EUR/USD, USD/GBP != 0.9988");
		assertTrue(results.get("GBP/USD, USD/EUR, EUR/GBP").equals(0.9937), "GBP/USD, USD/EUR, EUR/GBP != 0.9937");
		System.out.println("Passed:");
		results.entrySet().stream().forEach(e -> System.out.printf(" - Triangle %s gains %f\n", e.getKey(), e.getValue()));
	}

	@Test @Order(3)
	public void testCycledRecursiveDetector() {
		System.out.println("Testing testCycledRecursiveDetector()...");
		Map <String, Double> results = new ArbitrageDetection().cycleCurrencies(rates);
		results.entrySet().stream().forEach(e -> System.out.printf(" - Triangle %s gains %f\n", e.getKey(), e.getValue()));
		assertTrue(results.get("EUR/USD, USD/GBP, GBP/EUR").equals(0.9988), "EUR/USD, USD/GBP, GBP/EUR != 0.9988");
		assertTrue(results.get("USD/EUR, EUR/GBP, GBP/USD").equals(0.9937), "USD/EUR, EUR/GBP, GBP/USD != 0.9937");
	}
}
