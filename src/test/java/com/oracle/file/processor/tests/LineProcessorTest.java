package com.oracle.file.processor.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.oracle.file.processor.exceptions.ApplicationException;
import com.oracle.file.processor.pojos.FileRecord;
import com.oracle.file.processor.services.LineProcessor;

/**
 * This class tests LineProcessor
 * 
 * @author NBhasin
 *
 */
class LineProcessorTest {
	@Test
	@DisplayName("Parsed line results into object, and all fields are mapped correctly")
	void testParseLineIntoObject() {
		String line = "111111,2222,North,RedTeam,ProjectApple,1000s";
		FileRecord customerResord = LineProcessor.parseLineIntoObject(line);

		assertEquals("111111", customerResord.getCustomerId());
		assertEquals("2222", customerResord.getContractId());
		assertEquals("North", customerResord.getGeozone());
		assertEquals("RedTeam", customerResord.getTeamCode());
		assertEquals("ProjectApple", customerResord.getProjectCode());
		assertEquals("1000s", customerResord.getBuildDuration());
	}

	@Test
	@DisplayName("Parsed line results into object, and all fields are mapped correctly. Extra columns are ignored.")
	void testParseLineIntoObject_ExtraColumnsIgnored() {
		String line = "111111,2222,North,RedTeam,ProjectApple,1000s,extra,soextra";
		FileRecord customerResord = LineProcessor.parseLineIntoObject(line);

		assertEquals("111111", customerResord.getCustomerId());
		assertEquals("2222", customerResord.getContractId());
		assertEquals("North", customerResord.getGeozone());
		assertEquals("RedTeam", customerResord.getTeamCode());
		assertEquals("ProjectApple", customerResord.getProjectCode());
		assertEquals("1000s", customerResord.getBuildDuration());
	}

	@Test
	@DisplayName("Not enough columns in line being parsed.")
	void testNotEnoughColumns() {
		String line = "111111";
		Throwable exception = assertThrows(ApplicationException.class, () -> LineProcessor.parseLineIntoObject(line));
		assertEquals("Either not enough columns or empty value for columns in record 111111", exception.getMessage());
	}

	@Test
	@DisplayName("Line being parsed is null.")
	void testNullLine() {
		Throwable exception = assertThrows(ApplicationException.class, () -> LineProcessor.parseLineIntoObject(null));
		assertEquals("Received null for incoming line", exception.getMessage());
	}

	@Test
	@DisplayName("Line has no content just commas")
	void testParseLineIntoObject_EmptyColumns() {
		String line = ",,,,,";
		Throwable exception = assertThrows(ApplicationException.class, () -> LineProcessor.parseLineIntoObject(line));
		assertEquals("Either not enough columns or empty value for columns in record ,,,,,", exception.getMessage());
	}
	
	@ParameterizedTest
	@DisplayName("Build duration does not follow regex pattern ^[0-9]*[s]{1}$")
	//@formatter:off
	@CsvSource(value = { 
			"111111,2222,North,RedTeam,ProjectApple,5555ss:Build duration not in correct format 5555ss", 
			"111111,2222,North,RedTeam,ProjectApple,aaaas:Build duration not in correct format aaaas", 
			"111111,2222,North,RedTeam,ProjectApple,1111:Build duration not in correct format 1111" }, delimiter = ':')
	//@formatter:on
	void testParseLineIntoObject_BuildDurationNotFollowingRequiredPattern(String input, String expected) {
		Throwable exception = assertThrows(ApplicationException.class, () -> LineProcessor.parseLineIntoObject(input));
		assertEquals(expected, exception.getMessage());
	}

}
