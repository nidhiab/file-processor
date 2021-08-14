package com.oracle.file.processor;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.oracle.file.processor.pojos.FileRecord;
import com.oracle.file.processor.services.CustomFileReader;
import com.oracle.file.processor.services.StatisticsOrchestrator;

/**
 * FileProcessorApplication class hosts the entry point for this application.
 * This application reads a file, generates required statistics and then prints those.
 * @author NBhasin
 *
 */
public class FileProcessorApplication {
	static {
		try (InputStream is = FileProcessorApplication.class.getClassLoader().getResourceAsStream("logging.properties")) {
			LogManager.getLogManager().readConfiguration(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private static Logger logger = Logger.getLogger(FileProcessorApplication.class.getName());

	public static void main(String args[]) {
		logger.log(Level.INFO, "FileProcessorApplication...");
		List<FileRecord> recordsList = CustomFileReader.readFile("records.csv");
		StatisticsOrchestrator.process(recordsList);

	}
}
