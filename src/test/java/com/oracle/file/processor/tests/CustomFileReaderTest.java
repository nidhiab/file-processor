package com.oracle.file.processor.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.oracle.file.processor.exceptions.ApplicationException;
import com.oracle.file.processor.services.CustomFileReader;

/**
 * Test class for CustomFileReader
 * 
 * @author NBhasin
 *
 */
class CustomFileReaderTest {
	@Test
	@DisplayName("Reading file should result into 5 records being read")
	void testReadFileInClassPath() {
		assertEquals(5, CustomFileReader.readFileInClassPath("records.csv").size());
	}

	@Test
	@DisplayName("File does not exist")
	void testFileDoesNotExist() {
		Throwable exception = assertThrows(ApplicationException.class, () -> CustomFileReader.readFileInClassPath("recordsxxx.csv"));
		assertEquals("Could not find file recordsxxx.csv", exception.getMessage());
	}

	@Test
	@DisplayName("File is empty. Records returned should be 0.")
	void testEmptyFile() {
		assertEquals(0, CustomFileReader.readFileInClassPath("empty_file.csv").size());
	}

	@Test
	@DisplayName("Not enough columns in the lines in file")
	void testNotEnoughColumns() {
		Throwable exception = assertThrows(ApplicationException.class, () -> CustomFileReader.readFileInClassPath("not_enough_columns.csv"));
		assertEquals("Not enough columns in record 2343225,2345,us_east,RedTeam", exception.getMessage());
	}

	@Test
	@DisplayName("Build duration does not have digits preceding s.")
	void testParseLineIntoObject_BuildDurationNotInCorrectFormat() {
		Throwable exception = assertThrows(ApplicationException.class, () -> CustomFileReader.readFileInClassPath("build_duration_not_digits.csv"));
		assertEquals("Build duration not in correct format aaas", exception.getMessage());
	}

	@Test
	@DisplayName("Build duration does not end with s.")
	void testParseLineIntoObject_BuildDurationNotInCorrectFormatDoesNotEndWith() {
		Throwable exception = assertThrows(ApplicationException.class, () -> CustomFileReader.readFileInClassPath("build_duration_does_not_emd_with_s.csv"));
		assertEquals("Build duration not in correct format 33333", exception.getMessage());
	}
}
