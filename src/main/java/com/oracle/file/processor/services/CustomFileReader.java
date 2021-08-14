package com.oracle.file.processor.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.oracle.file.processor.exceptions.ApplicationException;
import com.oracle.file.processor.pojos.FileRecord;

/**
 * This class reads file corresponding to the passed in name and produces FileRecord objects for each read line
 * 
 * @author NBhasin
 *
 */
public final class CustomFileReader {
	private static Logger logger = Logger.getLogger("CustomFileReader");

	/**
	 * Private default constructor
	 */
	private CustomFileReader() {
		// Default con
	}

	/**
	 * This method accepts a file name. It finds it in the classpath, and creates a FileRecord object per line
	 * 
	 * 
	 * @param fileName - The file to be read
	 * @return - Collection of objects created from lines in file
	 */
	public static List<FileRecord> readFile(String fileName) {
		logger.log(Level.INFO, "Come to read file {0}", new Object[] { fileName });
		List<FileRecord> records = new ArrayList<>();
		InputStream inputStream = CustomFileReader.class.getClassLoader().getResourceAsStream(fileName);
		if (inputStream == null) {
			throw new ApplicationException(String.format("Could not find file %s", fileName));
		}
		try (InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8); BufferedReader reader = new BufferedReader(streamReader)) {
			String line = null;
			while ((line = reader.readLine()) != null) {
				FileRecord customerRecord = LineProcessor.parseLineIntoObject(line);
				records.add(customerRecord);
			}
		} catch (IOException e) {
			throw new ApplicationException(String.format("Error occurred while trying to read file %s", fileName), e);
		}
		return records;
	}

}
