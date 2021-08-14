package com.oracle.file.processor.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Handler;
import java.util.logging.LogManager;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.oracle.file.processor.services.StatisticsPrinter;

class StatisticsPrinterTest {
	static List<String> loggedMessagesList;

	/**
	 * This method prepares two sets of records for two test cases. It also is adding a custom logging handler so that we can count messages being logged as a result of each test
	 */
	@BeforeAll
	static void setUp() {
		LogManager.getLogManager().reset();
		Logger rootLogger = LogManager.getLogManager().getLogger("");
		LogCounter handler = new LogCounter();
		rootLogger.addHandler(handler);
	}

	/**
	 * This method is executed before each test and is responsible for clearing the logged messages list to prepare it for the next test
	 */
	@BeforeEach
	void setUpBeforeEachTest() {
		loggedMessagesList = new ArrayList<>();
	}

	@Test
	@DisplayName("Should print four messages. one header and three about 3 entries in map")
	void testPrintAverageBuildDurationPerGeozone() {
		Map<String, Double> averagesMap = new HashMap<>();
		averagesMap.put("Red", 1000.0);
		averagesMap.put("Blue", 2000.0);
		averagesMap.put("Green", 3000.0);
		StatisticsPrinter.printAverageBuildDurationPerGeozone(averagesMap);
		assertEquals(4, loggedMessagesList.size());
	}

	@Test
	@DisplayName("The average build duration per geozone is able to deal with empty map")
	void testPrintAverageBuildDurationPerGeozone_EmptyMap() {
		Map<String, Double> averagesMap = new HashMap<>();
		StatisticsPrinter.printAverageBuildDurationPerGeozone(averagesMap);
		assertEquals(0, loggedMessagesList.size());
	}

	@Test
	@DisplayName("The average build duration per geozone is able to deal with null map")
	void testPrintAverageBuildDurationPerGeozone_NullMap() {
		StatisticsPrinter.printAverageBuildDurationPerGeozone(null);
		assertEquals(0, loggedMessagesList.size());
	}

	@Test
	@DisplayName("Should print three messages. one header and 2 messages about unique customers count in zone")
	void testPrintUniqueCustomerCountPerGeozone() {
		Map<String, Map<String, Long>> customerPerGeozone = new HashMap<>();
		Map<String, Long> customerCountZone1 = new HashMap<>();
		customerCountZone1.put("1", 1L);
		customerCountZone1.put("2", 2L);
		customerPerGeozone.put("Zone1", customerCountZone1);

		Map<String, Long> customerCountZone2 = new HashMap<>();
		customerCountZone2.put("1", 2L);
		customerCountZone2.put("2", 1L);
		customerPerGeozone.put("Zone2", customerCountZone2);

		StatisticsPrinter.printUniqueCustomerCountPerGeozone(customerPerGeozone);
		assertEquals(3, loggedMessagesList.size());
	}

	@Test
	@DisplayName("The method to print unique customer count per geozone is able to deal with empty map")
	void testPrintUniqueCustomerCountPerGeozone_EmptyMap() {
		Map<String, Map<String, Long>> customerPerGeozone = new HashMap<>();

		StatisticsPrinter.printUniqueCustomerCountPerGeozone(customerPerGeozone);
		assertEquals(0, loggedMessagesList.size());
	}

	@Test
	@DisplayName("The method to print unique customer count per geozone is able to deal with null map")
	void testPrintUniqueCustomerCountPerGeozone_NullMap() {
		StatisticsPrinter.printUniqueCustomerCountPerGeozone(null);
		assertEquals(0, loggedMessagesList.size());
	}

	@Test
	@DisplayName("The method to print unique customer count per geozone is able to deal with empty inner map containing no customer details")
	void testPrintUniqueCustomerCountPerGeozone_EmptyInnerMap() {
		Map<String, Map<String, Long>> customerPerGeozone = new HashMap<>();
		Map<String, Long> customerCountZone1 = new HashMap<>();
		customerPerGeozone.put("Zone1", customerCountZone1);

		Map<String, Long> customerCountZone2 = new HashMap<>();
		customerPerGeozone.put("Zone2", customerCountZone2);

		StatisticsPrinter.printUniqueCustomerCountPerGeozone(customerPerGeozone);
		assertEquals(3, loggedMessagesList.size());
	}

	@Test
	@DisplayName("The method to print unique customer count per geozone is able to deal with null inner map containing no customer details")
	void testPrintUniqueCustomerCountPerGeozone_NullInnerMap() {
		Map<String, Map<String, Long>> customerPerGeozone = new HashMap<>();
		customerPerGeozone.put("Zone1", null);
		customerPerGeozone.put("Zone2", null);

		StatisticsPrinter.printUniqueCustomerCountPerGeozone(customerPerGeozone);
		assertEquals(1, loggedMessagesList.size());
	}

	@Test
	@DisplayName("Should print three messages. one header and 2 messages with unique customers in zone")
	void testPrintUniqueCustomerPerGeozone() {
		Map<String, Map<String, Long>> customerPerGeozone = new HashMap<>();
		Map<String, Long> customerCountZone1 = new HashMap<>();
		customerCountZone1.put("1", 1L);
		customerCountZone1.put("2", 2L);
		customerPerGeozone.put("Orange", customerCountZone1);

		Map<String, Long> customerCountZone2 = new HashMap<>();
		customerCountZone2.put("3", 2L);
		customerCountZone2.put("4", 1L);
		customerPerGeozone.put("Neon", customerCountZone2);

		StatisticsPrinter.printUniqueCustomerPerGeozone(customerPerGeozone);
		assertEquals(3, loggedMessagesList.size());
	}

	@Test
	@DisplayName("The method to print unique customer per geozone is able to deal with empty map")
	void testPrintUniqueCustomerPerGeozone_EmptyMap() {
		Map<String, Map<String, Long>> customerPerGeozone = new HashMap<>();
		StatisticsPrinter.printUniqueCustomerPerGeozone(customerPerGeozone);
		assertEquals(0, loggedMessagesList.size());
	}

	@Test
	@DisplayName("The method to print unique customer per geozone is able to deal with null map")
	void testPrintUniqueCustomerPerGeozone_NullyMap() {
		StatisticsPrinter.printUniqueCustomerPerGeozone(null);
		assertEquals(0, loggedMessagesList.size());
	}

	@Test
	@DisplayName("The method to print unique customer per geozone is able to deal with empty inner map containing no customer details")
	void testPrintUniqueCustomerPerGeozone_EmptyInnerMap() {
		Map<String, Map<String, Long>> customerPerGeozone = new HashMap<>();
		Map<String, Long> customerCountZone1 = new HashMap<>();
		customerPerGeozone.put("Orange", customerCountZone1);

		Map<String, Long> customerCountZone2 = new HashMap<>();
		customerPerGeozone.put("Neon", customerCountZone2);

		StatisticsPrinter.printUniqueCustomerPerGeozone(customerPerGeozone);
		assertEquals(3, loggedMessagesList.size());
	}

	@Test
	@DisplayName("The method to print unique customer per geozone is able to deal with null inner map containing no customer details")
	void testPrintUniqueCustomerPerGeozone_NullInnerMap() {
		Map<String, Map<String, Long>> customerPerGeozone = new HashMap<>();
		customerPerGeozone.put("Orange", null);
		customerPerGeozone.put("Neon", null);

		StatisticsPrinter.printUniqueCustomerPerGeozone(customerPerGeozone);
		assertEquals(1, loggedMessagesList.size());
	}

	@Test
	@DisplayName("Should print four messages. one header and 3 messages about unique customers and their count in contract")
	void testPrintUniqueCustomerCountAndCustomerPerContract() {
		Map<String, Map<String, Long>> customerPerGeozone = new HashMap<>();
		Map<String, Long> customerCountContract1111 = new HashMap<>();
		customerCountContract1111.put("1", 5L);
		customerCountContract1111.put("2", 1L);
		customerPerGeozone.put("1111", customerCountContract1111);

		Map<String, Long> customerCountContract2222 = new HashMap<>();
		customerCountContract2222.put("1", 10L);
		customerPerGeozone.put("2222", customerCountContract2222);

		Map<String, Long> customerCountContract3333 = new HashMap<>();
		customerCountContract3333.put("4", 1L);
		customerPerGeozone.put("3333", customerCountContract3333);

		StatisticsPrinter.printUniqueCustomerPerContract(customerPerGeozone);
		assertEquals(4, loggedMessagesList.size());
	}

	@Test
	@DisplayName("The method to print unique customer count and customer per contract is able to deal with empty map")
	void testPrintUniqueCustomerCountAndCustomerPerContract_EmptyMap() {
		Map<String, Map<String, Long>> customerPerGeozone = new HashMap<>();
		StatisticsPrinter.printUniqueCustomerPerContract(customerPerGeozone);
		assertEquals(0, loggedMessagesList.size());
	}

	@Test
	@DisplayName("The method to print unique customer count and customer per contract is able to deal with null map")
	void testPrintUniqueCustomerCountAndCustomerPerContract_NullMap() {
		StatisticsPrinter.printUniqueCustomerPerContract(null);
		assertEquals(0, loggedMessagesList.size());
	}

	@Test
	@DisplayName("The method to print unique customer count and customer per contract is able to deal with empty inner map with no customer details")
	void testPrintUniqueCustomerCountAndCustomerPerContract_EmptyInnerMap() {
		Map<String, Map<String, Long>> customerPerGeozone = new HashMap<>();
		Map<String, Long> customerCountContract1111 = new HashMap<>();
		customerPerGeozone.put("1111", customerCountContract1111);

		Map<String, Long> customerCountContract2222 = new HashMap<>();
		customerPerGeozone.put("2222", customerCountContract2222);

		Map<String, Long> customerCountContract3333 = new HashMap<>();
		customerPerGeozone.put("3333", customerCountContract3333);

		StatisticsPrinter.printUniqueCustomerPerContract(customerPerGeozone);
		assertEquals(4, loggedMessagesList.size());
	}

	@Test
	@DisplayName("The method to print unique customer count and customer per contract is able to deal with null inner map with no customer details")
	void testPrintUniqueCustomerCountAndCustomerPerContract_NullInnerMap() {
		Map<String, Map<String, Long>> customerPerGeozone = new HashMap<>();
		customerPerGeozone.put("1111", null);
		customerPerGeozone.put("2222", null);
		customerPerGeozone.put("3333", null);

		StatisticsPrinter.printUniqueCustomerPerContract(customerPerGeozone);
		assertEquals(1, loggedMessagesList.size());
	}

	static class LogCounter extends Handler {
		@Override
		public void publish(LogRecord record) {
			String message = record.getMessage();
			if (message != null) {
				final Object[] params = record.getParameters();
				if (params != null && params.length > 0) {
					message = MessageFormat.format(message, params);
				}
			}
			System.out.println(message);
			loggedMessagesList.add(message);
		}

		@Override
		public void flush() {
			// No impl required
		}

		@Override
		public void close() throws SecurityException {
			// No impl required
		}
	}
}
