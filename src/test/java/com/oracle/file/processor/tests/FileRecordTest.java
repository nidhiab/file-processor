package com.oracle.file.processor.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.oracle.file.processor.pojos.FileRecord;

/**
 * This class tests creation of FileRecord object via fluent builder pattern
 * @author NBhasin
 *
 */
class FileRecordTest {

	@Test
	@DisplayName("Object creation through builder happens correctly. Attributes are mapped correctly")
	void testObjectCreation() {
		String customerId = "111111";
		String contractId = "2222";
		String geozone = "North";
		String teamCode = "RedTeam";
		String projectCode = "ProjectApple";
		String buildDuration = "1000s";

		//@formatter:off
		FileRecord customerResord = FileRecord.FileRecordBuilder.getInstance()
		.setCustomerId(customerId)
		.setContractId(contractId)
		.setGeozone(geozone)
		.setTeamCode(teamCode)
		.setProjectCode(projectCode)
		.setBuildDuration(buildDuration)
		.build();
		//@formatter:on

		assertEquals(customerId, customerResord.getCustomerId());
		assertEquals(contractId, customerResord.getContractId());
		assertEquals(geozone, customerResord.getGeozone());
		assertEquals(teamCode, customerResord.getTeamCode());
		assertEquals(projectCode, customerResord.getProjectCode());
		assertEquals(buildDuration, customerResord.getBuildDuration());
	}

}
