package com.oracle.file.processor.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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
	void testReadFileInClassPath_FileDoesNotExist() {
		Throwable exception = assertThrows(ApplicationException.class, () -> CustomFileReader.readFileInClassPath("recordsxxx.csv"));
		assertEquals("Could not find file recordsxxx.csv", exception.getMessage());
	}

	@Test
	@DisplayName("File is empty. Records returned should be 0.")
	void testReadFileInClassPath_EmptyFile() {
		assertEquals(0, CustomFileReader.readFileInClassPath("empty_file.csv").size());
	}

	@Test
	@DisplayName("Not enough columns in the lines in file")
	void testReadFileInClassPath_NotEnoughColumns() {
		Throwable exception = assertThrows(ApplicationException.class, () -> CustomFileReader.readFileInClassPath("not_enough_columns.csv"));
		assertEquals("Either not enough columns or empty value for columns in record 2343225,2345,us_east,RedTeam", exception.getMessage());
	}

	@Test
	@DisplayName("Line has no content just commas")
	void testReadFileInClassPath_EmptyColumns() {
		Throwable exception = assertThrows(ApplicationException.class, () -> CustomFileReader.readFileInClassPath("empty_values_columns.csv"));
		assertEquals("Either not enough columns or empty value for columns in record ,,,,,", exception.getMessage());
	}

	@ParameterizedTest
	@DisplayName("Build duration does not follow regex pattern ^[0-9]*[s]{1}$")
	//@formatter:off
	@CsvSource(value = { 
			"build_duration_not_digits.csv:Build duration not in correct format aaas", 
			"build_duration_does_not_end_with_s.csv:Build duration not in correct format 33333" }, delimiter = ':')
	//@formatter:on
	void testReadFileInClassPath_BuildDurationNotFollowingRequiredPattern(String input, String expected) {
		Throwable exception = assertThrows(ApplicationException.class, () -> CustomFileReader.readFileInClassPath(input));
		assertEquals(expected, exception.getMessage());
	}

}
