package com.oracle.file.processor.services;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.oracle.file.processor.pojos.FileRecord;

/**
 * This class is responsible for analyzing the incoming records for required statistics
 * @author NBhasin
 *
 */
public final class StatisticsGenerator {
	/**
	 * Private default constructor
	 */
	private StatisticsGenerator() {
		// Private default con
	}

	/**
	 * This method accepts a list of FileRecord objects and returns a map with average build duration per geozone
	 * 
	 * @param recordList - The records to be processed
	 * @return - a map with average build durations
	 */
	public static Map<String, Double> getAverageBuildDurationPerGeozone(List<FileRecord> recordList) {
		return recordList.stream().collect(Collectors.groupingBy(FileRecord::getGeozone,
				Collectors.averagingDouble(d -> Integer.valueOf(d.getBuildDuration().replace("s", "")))));

	}

	/**
	 * This method accepts a list of FileRecord objects and returns a map that has customerIds against a contract and their count
	 * 
	 * @param recordList - The records to be processed
	 * @return - a map with customers against a contract with their count
	 */
	public static Map<String, Map<String, Long>> getUniqueCustomerPerContract(
			List<FileRecord> recordList) {
		return recordList.stream().collect(Collectors.groupingBy(FileRecord::getContractId,
				Collectors.groupingBy(FileRecord::getCustomerId, Collectors.counting())));

	}

	/**
	 * This method accepts a list of FileRecord objects and returns a map that has customerIds against a geozone and their count
	 * 
	 * @param recordList - The records to be processed
	 * @return - a map with customers against a geozone with their count
	 */
	public static Map<String, Map<String, Long>> getUniqueCustomerPerGeozone(
			List<FileRecord> recordList) {
		return recordList.stream().collect(Collectors.groupingBy(FileRecord::getGeozone,
				Collectors.groupingBy(FileRecord::getCustomerId, Collectors.counting())));
	}
}
