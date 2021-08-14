package com.oracle.file.processor.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogManager;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.oracle.file.processor.pojos.FileRecord;
import com.oracle.file.processor.services.LineProcessor;
import com.oracle.file.processor.services.StatisticsOrchestrator;

class StatisticsOrchestratorTest {
	static List<FileRecord> testRecordsSetOne = new ArrayList<>();
	static List<FileRecord> testRecordsSetTwo = new ArrayList<>();
	static List<String> loggedMessagesList;

	/**
	 * This method prepares two sets of records for two test cases. It also is adding a custom logging handler so that we can count messages being logged as a result of each test
	 */
	@BeforeAll
	static void setUp() {
		String line1 = "111111,2222,us_east,RedTeam,ProjectApple,1000s";
		String line2 = "111111,2222,us_west,BlueTeam,ProjectBanana,2000s";
		String line3 = "111111,3333,eu_west,YellowTeam3,ProjectCarrot,1500s";
		String line4 = "222222,4444,us_west,BlueTeam,ProjectDate,1000s";
		String line5 = "333333,2222,eu_west,YellowTeam3,ProjectEgg,2000s";

		String line6 = "333333,3333,eu_west,YellowTeam3,ProjectEgg,4000s";
		String line7 = "444444,4444,eu_west,YellowTeam3,ProjectEgg,1500s";
		String line8 = "444444,4444,eu_east,YellowTeam3,ProjectEgg,2500s";
		String line9 = "555555,2222,eu_east,YellowTeam3,ProjectEgg,3000s";
		String line10 = "555555,3333,eu_west,YellowTeam3,ProjectEgg,3500s";

		testRecordsSetOne.add(LineProcessor.parseLineIntoObject(line1));
		testRecordsSetOne.add(LineProcessor.parseLineIntoObject(line2));
		testRecordsSetOne.add(LineProcessor.parseLineIntoObject(line3));
		testRecordsSetOne.add(LineProcessor.parseLineIntoObject(line4));
		testRecordsSetOne.add(LineProcessor.parseLineIntoObject(line5));
		testRecordsSetOne.add(LineProcessor.parseLineIntoObject(line6));
		testRecordsSetOne.add(LineProcessor.parseLineIntoObject(line7));

		testRecordsSetTwo.add(LineProcessor.parseLineIntoObject(line6));
		testRecordsSetTwo.add(LineProcessor.parseLineIntoObject(line7));
		testRecordsSetTwo.add(LineProcessor.parseLineIntoObject(line8));
		testRecordsSetTwo.add(LineProcessor.parseLineIntoObject(line9));
		testRecordsSetTwo.add(LineProcessor.parseLineIntoObject(line10));

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
	@DisplayName("Record Processing should result into seven records being read and statistics generated and printed.")
	void testProcess_FirstSet() {
		StatisticsOrchestrator.process(testRecordsSetOne);
		assertEquals(21, loggedMessagesList.size());
	}

	@Test
	@DisplayName("Record Processing should result into five records being read and statistics generated and printed.")
	void testProcess_SecondSet() {
		loggedMessagesList = new ArrayList<>();
		StatisticsOrchestrator.process(testRecordsSetTwo);
		assertEquals(18, loggedMessagesList.size());
	}

	@Test
	@DisplayName("The method is able to deal with empty record set.")
	void testProcess_EmptyRecordSet() {
		StatisticsOrchestrator.process(new ArrayList<>());
		assertEquals(0, loggedMessagesList.size());
	}

	@Test
	@DisplayName("The method is able to deal with null record set.")
	void testProcess_NullRecordSet() {
		StatisticsOrchestrator.process(null);
		assertEquals(0, loggedMessagesList.size());
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
