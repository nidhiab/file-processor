package com.oracle.file.processor.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.oracle.file.processor.pojos.FileRecord;
import com.oracle.file.processor.services.LineProcessor;
import com.oracle.file.processor.services.StatisticsGenerator;

class StatisticsGeneratorTest {
	static List<FileRecord> testRecords = new ArrayList<>();

	@BeforeAll
	static void setUp() {
		String line1 = "111111,2222,North,RedTeam,ProjectApple,1000s";
		String line2 = "111111,2222,North,YellowTeam3,ProjectEgg,2000s";
		String line3 = "555555,2222,North,YellowTeam3,ProjectEgg,3000s";

		String line4 = "111111,2222,East,BlueTeam,ProjectBanana,2000s";
		String line5 = "333333,3333,East,YellowTeam3,ProjectEgg,4000s";
		String line6 = "555555,3333,East,YellowTeam3,ProjectEgg,3000s";

		String line7 = "111111,3333,West,YellowTeam3,ProjectCarrot,1500s";
		String line8 = "444444,4444,West,YellowTeam3,ProjectEgg,1500s";

		String line9 = "222222,4444,South,BlueTeam,ProjectDate,1000s";
		String line10 = "222222,4444,South,YellowTeam3,ProjectEgg,2500s";

		testRecords.add(LineProcessor.parseLineIntoObject(line1));
		testRecords.add(LineProcessor.parseLineIntoObject(line2));
		testRecords.add(LineProcessor.parseLineIntoObject(line3));
		testRecords.add(LineProcessor.parseLineIntoObject(line4));
		testRecords.add(LineProcessor.parseLineIntoObject(line5));
		testRecords.add(LineProcessor.parseLineIntoObject(line6));
		testRecords.add(LineProcessor.parseLineIntoObject(line7));
		testRecords.add(LineProcessor.parseLineIntoObject(line8));
		testRecords.add(LineProcessor.parseLineIntoObject(line9));
		testRecords.add(LineProcessor.parseLineIntoObject(line10));
	}

	@Test
	@DisplayName("Calculate average build for the passed in test records that correspond to four zones.")
	void testGetAverageBuildDurationPerGeozone() {
		Map<String, Double> map = StatisticsGenerator.getAverageBuildDurationPerGeozone(testRecords);

		assertEquals(4, map.size()); // we have 4 zones

		assertEquals(2000.0, map.get("North")); // average of 1000 + 2000 + 3000
		assertEquals(3000.0, map.get("East")); // average of 2000 + 4000 + 3000
		assertEquals(1500.0, map.get("West")); // average of 1500 + 1500
		assertEquals(1750.0, map.get("South")); // average of 1000 + 2500
	}

	@Test
	@DisplayName("The average build duration calculator method is able to deal with empty record set.")
	void testGetAverageBuildDurationPerGeozone_EmptyRecordSet() {
		Map<String, Double> map = StatisticsGenerator.getAverageBuildDurationPerGeozone(new ArrayList<>());
		assertEquals(0, map.size()); // empty record set
	}

	@Test
	@DisplayName("Get Unique Customer details and count per geozone. The test records correspond to four zones.")
	void testGetUniqueCustomerCountAndCustomerPerGeozone() {
		Map<String, Map<String, Long>> map = StatisticsGenerator.getUniqueCustomerPerGeozone(testRecords);

		assertEquals(4, map.size()); // we have 4 zones

		Map<String, Long> northMap = map.get("North");
		Map<String, Long> eastMap = map.get("East");
		Map<String, Long> westMap = map.get("West");
		Map<String, Long> southMap = map.get("South");

		assertEquals(2, northMap.size()); // north has 2 unique customers 111111 and 555555
		assertEquals(2, northMap.get("111111")); // north has three records out of which 111111 appears in 2
		assertEquals(1, northMap.get("555555")); // north has three records out of which 555555 appears in 1

		assertEquals(3, eastMap.size()); // east has 3 unique customers 111111, 333333 and 555555
		assertEquals(1, eastMap.get("111111")); // east has three records and 111111 appears only once
		assertEquals(1, eastMap.get("333333")); // east has three records and 333333 appears only once
		assertEquals(1, eastMap.get("555555")); // east has three records and 555555 appears only once

		assertEquals(2, westMap.size()); // west has 2 unique customers 111111 and 444444
		assertEquals(1, westMap.get("111111")); // west has two records and 111111 appears only once
		assertEquals(1, westMap.get("444444")); // west has two records and 444444 appears only once

		assertEquals(1, southMap.size()); // south has 1 unique customer 222222
		assertEquals(2, southMap.get("222222")); // south has two records and 222222 appears in both
	}

	@Test
	@DisplayName("The unique customer per geozone calculator method is able to deal with empty record set.")
	void testGetUniqueCustomerCountAndCustomerPerGeozone_EmptyRecordSet() {
		Map<String, Map<String, Long>> map = StatisticsGenerator.getUniqueCustomerPerGeozone(new ArrayList<>());

		assertEquals(0, map.size()); // empty record set
	}

	@Test
	@DisplayName("Get Unique Customer details and count per contract. The test records correspond to three contracts.")
	void testGetUniqueCustomerPerContract() {
		Map<String, Map<String, Long>> map = StatisticsGenerator.getUniqueCustomerPerContract(testRecords);

		assertEquals(3, map.size()); // we have 3 contracts
		Map<String, Long> contract2222Map = map.get("2222");
		Map<String, Long> contract3333Map = map.get("3333");
		Map<String, Long> contract4444Map = map.get("4444");

		assertEquals(2, contract2222Map.size()); // contract 2222 has 2 unique customers 111111 and 555555
		assertEquals(3, contract2222Map.get("111111")); // contract 2222 has four records and 111111 appears in 3
		assertEquals(1, contract2222Map.get("555555")); // contract 2222 has four records and 555555 appears in 1

		assertEquals(3, contract3333Map.size()); // contract 3333 has 3 unique customers 111111, 333333 and 555555
		assertEquals(1, contract3333Map.get("111111")); // contract 3333 has three records and 111111 appears only once
		assertEquals(1, contract3333Map.get("333333")); // contract 3333 has three records and 333333 appears only once
		assertEquals(1, contract3333Map.get("555555")); // contract 3333 has three records and 555555 appears only once

		assertEquals(2, contract4444Map.size()); // contract 4444 has 2 unique customers 444444 and 222222
		assertEquals(1, contract4444Map.get("444444")); // contract 4444 has three records and 444444 appears in 1
		assertEquals(2, contract4444Map.get("222222")); // contract 4444 has three records and 222222 appears in 2
	}

	@Test
	@DisplayName("The unique customer per contract calculator method is able to deal with empty record set.")
	void testGetUniqueCustomerPerContract_EmptyRecordSet() {
		Map<String, Map<String, Long>> map = StatisticsGenerator.getUniqueCustomerPerContract(new ArrayList<>());
		assertEquals(0, map.size()); // empty record set
	}

}
