package com.oracle.file.processor.services;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class hosts methods that are responsible for printing very specific statistics. 
 * The input passed in is of the shape that will allow the required information to be printed.
 * 
 * @author NBhasin
 *
 */
public final class StatisticsPrinter {
	private static Logger logger = Logger.getLogger("StatisticsPrinter");

	/**
	 * Private default constructor
	 */
	private StatisticsPrinter() {
		// Default con
	}

	/**
	 * This method prints average build duration per geozone
	 * 
	 * @param map - Map that holds geozone and average build duration
	 */
	public static void printAverageBuildDurationPerGeozone(Map<String, Double> map) {
		if (!isNullOrEmptyMap(map)) {
			logger.log(Level.INFO, "Going to find averages");
			for (var entry : map.entrySet()) {
				if (entry.getValue() != null) {
					logger.log(Level.INFO, "Geozone {0} average build duration {1}", new Object[] { entry.getKey(), entry.getValue() });
				}
			}
		}
	}

	/**
	 * This method prints the count and unique customer ids per contract
	 * 
	 * @param map - Map that holds contract with customer and customer count
	 */
	public static void printUniqueCustomerPerContract(Map<String, Map<String, Long>> map) {
		if (!isNullOrEmptyMap(map)) {
			logger.log(Level.INFO, "Going to find unique customers per contract");
			for (var entry : map.entrySet()) {
				if (entry.getValue() != null) {
					logger.log(Level.INFO, "Contract {0} has {1} unique customers {2}", new Object[] { entry.getKey(), entry.getValue().size(), entry.getValue().keySet() });
				}
			}
		}
	}

	/**
	 * This method prints unique customer count per geozone
	 * 
	 * @param map - Map that holds geozone with customer and customer count
	 */
	public static void printUniqueCustomerCountPerGeozone(Map<String, Map<String, Long>> map) {
		if (!isNullOrEmptyMap(map)) {
			logger.log(Level.INFO, "Going to find unique customers count per geozone");
			for (var entry : map.entrySet()) {
				if (entry.getValue() != null) {
					logger.log(Level.INFO, "Geozone {0} has {1} unique customers", new Object[] { entry.getKey(), entry.getValue().size() });
				}
			}
		}
	}

	/**
	 * This method prints unique customer ids per geozone
	 * 
	 * @param map - Map that holds geozone with customer and customer count
	 */
	public static void printUniqueCustomerPerGeozone(Map<String, Map<String, Long>> map) {
		if (!isNullOrEmptyMap(map)) {
			logger.log(Level.INFO, "Going to find unique customers per geozone");
			for (var entry : map.entrySet()) {
				if (entry.getValue() != null) {
					logger.log(Level.INFO, "Geozone {0} has unique customers {1}", new Object[] { entry.getKey(), entry.getValue().keySet() });
				}
			}
		}
	}

	/**
	 * This method checks if the incoming map is null or empty
	 * 
	 * @param map - incoming map
	 * @return - true if map is null or empty, false if neither null nor empty
	 */
	private static boolean isNullOrEmptyMap(Map<?, ?> map) {
		return (map == null || map.isEmpty());
	}
}
