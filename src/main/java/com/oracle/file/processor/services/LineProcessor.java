package com.oracle.file.processor.services;

import com.oracle.file.processor.exceptions.ApplicationException;
import com.oracle.file.processor.pojos.FileRecord;

/**
 * This class is responsible for parsing a line and creating a corresponding FileRecord
 * 
 * @author NBhasin
 *
 */
public final class LineProcessor {
	private static final String BUILD_DURATION_PATTERN = "^[0-9]*[s]{1}$";

	/**
	 * Private default constructor
	 */
	private LineProcessor() {
		// Default con
	}

	/**
	 * This method parses an incoming line, splits it by comma, and produces a FileRecord object. 
	 * It throws a runtime exception if-
	 * it receives null for the line. 
	 * the number of columns are fewer than 6. 
	 * any of the columns is null or empty/blank. 
	 * build duration does not follow the pattern of all digits followed by single character s  (^[0-9]*[s]{1}$)
	 * 
	 * If the number of columns are greater than 6, it ignores the rest.
	 * 
	 * @param line - to process
	 * @return created FileRecord
	 */
	public static FileRecord parseLineIntoObject(String line) {
		if (line == null) {
			throw new ApplicationException("Received null for incoming line");
		}
		String[] columnsArray = line.trim().split(","); // Ignoring empty columns. Every column is mandatory and not empty/blank String for our purposes.
		if (columnsArray.length < 6) {
			throw new ApplicationException(String.format("Either not enough columns or empty value for columns in record %s", line));
		}
		String buildDurationColumn = columnsArray[5].trim();
		if (!buildDurationColumn.matches(BUILD_DURATION_PATTERN)) {
			throw new ApplicationException(String.format("Build duration not in correct format %s", buildDurationColumn));
		}
		//@formatter:off
		return
		FileRecord.FileRecordBuilder.getInstance()
		.setCustomerId(columnsArray[0].trim())
		.setContractId(columnsArray[1].trim())
		.setGeozone(columnsArray[2].trim())
		.setTeamCode(columnsArray[3].trim())
		.setProjectCode(columnsArray[4].trim())
		.setBuildDuration(buildDurationColumn)
		.build();
		//@formatter:on
	}
}
