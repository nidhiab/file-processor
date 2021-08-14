package com.oracle.file.processor.services;

import com.oracle.file.processor.exceptions.ApplicationException;
import com.oracle.file.processor.pojos.FileRecord;

/**
 * This class is responsible for parsing a line and creating a corresponding FileRecord
 * 
 * @author NBhasin
 *
 */
public class LineProcessor {

	/**
	 * Private default constructor
	 */
	private LineProcessor() {
		// Default con
	}

	/**
	 * This method parses an incoming line, splits it by comma, and produces a FileRecord object. 
	 * It throws a runtime exception if it receives null for the line. 
	 * It throws a runtime exception if the number of columns are fewer than 6. 
	 * If the number of columns are greater than 6, it ignores the rest.
	 * 
	 * @param line - to process
	 * @return created FileRecord
	 */
	public static FileRecord parseLineIntoObject(String line) {
		if (line == null) {
			throw new ApplicationException("Received null for incoming line");
		}
		String[] columnsArray = line.trim().split(",");
		if (columnsArray.length < 6) {
			throw new ApplicationException(String.format("Not enough columns in record %s", line));
		}
		//@formatter:off
		return
		FileRecord.FileRecordBuilder.getInstance()
		.setCustomerId(columnsArray[0])
		.setContractId(columnsArray[1])
		.setGeozone(columnsArray[2])
		.setTeamCode(columnsArray[3])
		.setProjectCode(columnsArray[4])
		.setBuildDuration(columnsArray[5])
		.build();
		//@formatter:on
	}
}
