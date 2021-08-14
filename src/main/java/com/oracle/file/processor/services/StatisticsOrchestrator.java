package com.oracle.file.processor.services;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.oracle.file.processor.pojos.FileRecord;

/**
 * This class processes a list of FileRecord objects, generates statistics for them, and then prints the statistics.
 * 
 * @author NBhasin
 *
 */
public class StatisticsOrchestrator {
	private static Logger logger = Logger.getLogger("StatisticsOrchestrator");
	private static final String LOG_SEPARATOR = "------------------------------------------------";

	/**
	 * Private default constructor
	 */
	private StatisticsOrchestrator() {
		// Default con
	}

	/**
	 * This method accepts a list of records, generates required statistics and prints them
	 * 
	 * @param recordList - The records to be processed
	 */
	public static void process(List<FileRecord> recordList) {
		if (recordList != null && !recordList.isEmpty()) {
			logger.log(Level.INFO, "Come to process records");
			Map<String, Map<String, Long>> customerPerContractMap = StatisticsGenerator.getUniqueCustomerPerContract(recordList);
			Map<String, Map<String, Long>> customerPerGeozone = StatisticsGenerator.getUniqueCustomerPerGeozone(recordList);
			Map<String, Double> averageBuildDurationPreGeozone = StatisticsGenerator.getAverageBuildDurationPerGeozone(recordList);
			logger.log(Level.INFO, LOG_SEPARATOR);
			StatisticsPrinter.printUniqueCustomerPerContract(customerPerContractMap);
			logger.log(Level.INFO, LOG_SEPARATOR);
			StatisticsPrinter.printUniqueCustomerCountPerGeozone(customerPerGeozone);
			logger.log(Level.INFO, LOG_SEPARATOR);
			StatisticsPrinter.printUniqueCustomerPerGeozone(customerPerGeozone);
			logger.log(Level.INFO, LOG_SEPARATOR);
			StatisticsPrinter.printAverageBuildDurationPerGeozone(averageBuildDurationPreGeozone);
		}
	}

}
