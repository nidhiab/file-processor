package com.oracle.file.processor.pojos;

/**
 * This object represents FileRecord  object. This encompasses a builder following fluent builder pattern, that aids in creation of this object.
 * 
 * @author NBhasin
 *
 */
public final class FileRecord {
	private String customerId;
	private String contractId;
	private String geozone;
	private String teamCode;
	private String projectCode;
	private String buildDuration;

	public FileRecord(String customerId, String contractId, String geozone, String teamCode, String projectCode, String buildDuration) {
		super();
		this.customerId = customerId;
		this.contractId = contractId;
		this.geozone = geozone;
		this.teamCode = teamCode;
		this.projectCode = projectCode;
		this.buildDuration = buildDuration;
	}

	public String getCustomerId() {
		return customerId;
	}

	public String getContractId() {
		return contractId;
	}

	public String getGeozone() {
		return geozone;
	}

	public String getTeamCode() {
		return teamCode;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public String getBuildDuration() {
		return buildDuration;
	}

	/**
	 * This is the builder class for creation of FileRecord objects
	 * 
	 * @author NBhasin
	 *
	 */
	public static class FileRecordBuilder implements CustomerId, ContractId, Geozone, TeamCode, ProjectCode, BuildDuration, FileRecordCreator {
		private String customerId;
		private String contractId;
		private String geozone;
		private String teamCode;
		private String projectCode;
		private String buildDuration;

		/**
		 * Private default con
		 */
		private FileRecordBuilder() {
		}

		public static CustomerId getInstance() {
			return new FileRecordBuilder();
		}

		@Override
		public FileRecord build() {
			return new FileRecord(customerId, contractId, geozone, teamCode, projectCode, buildDuration);
		}

		@Override
		public FileRecordCreator setBuildDuration(String buildDuration) {
			this.buildDuration = buildDuration;
			return this;
		}

		@Override
		public BuildDuration setProjectCode(String projectCode) {
			this.projectCode = projectCode;
			return this;
		}

		@Override
		public ProjectCode setTeamCode(String teamCode) {
			this.teamCode = teamCode;
			return this;
		}

		@Override
		public TeamCode setGeozone(String geozone) {
			this.geozone = geozone;
			return this;
		}

		@Override
		public Geozone setContractId(String contractId) {
			this.contractId = contractId;
			return this;
		}

		@Override
		public ContractId setCustomerId(String customerId) {
			this.customerId = customerId;
			return this;
		}

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((buildDuration == null) ? 0 : buildDuration.hashCode());
		result = prime * result + ((contractId == null) ? 0 : contractId.hashCode());
		result = prime * result + ((customerId == null) ? 0 : customerId.hashCode());
		result = prime * result + ((geozone == null) ? 0 : geozone.hashCode());
		result = prime * result + ((projectCode == null) ? 0 : projectCode.hashCode());
		result = prime * result + ((teamCode == null) ? 0 : teamCode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FileRecord other = (FileRecord) obj;
		if (buildDuration == null) {
			if (other.buildDuration != null)
				return false;
		} else if (!buildDuration.equals(other.buildDuration))
			return false;
		if (contractId == null) {
			if (other.contractId != null)
				return false;
		} else if (!contractId.equals(other.contractId))
			return false;
		if (customerId == null) {
			if (other.customerId != null)
				return false;
		} else if (!customerId.equals(other.customerId))
			return false;
		if (geozone == null) {
			if (other.geozone != null)
				return false;
		} else if (!geozone.equals(other.geozone))
			return false;
		if (projectCode == null) {
			if (other.projectCode != null)
				return false;
		} else if (!projectCode.equals(other.projectCode))
			return false;
		if (teamCode == null) {
			if (other.teamCode != null)
				return false;
		} else if (!teamCode.equals(other.teamCode))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FileRecord [customerId=" + customerId + ", contractId=" + contractId + ", geozone=" + geozone + ", teamCode=" + teamCode + ", projectCode=" + projectCode + ", buildDuration=" + buildDuration + "]";
	}

	/**
	 * CustomerId interface
	 * 
	 * @author NBhasin
	 *
	 */
	public interface CustomerId {
		/**
		 * Allows setting customer Id and returns ContractId type for further chaining
		 * 
		 * @param customerId - the customer id
		 * @return ContractId
		 */
		ContractId setCustomerId(String customerId);
	}

	/**
	 * Contract Id interface
	 * 
	 * @author NBhasin
	 *
	 */
	public interface ContractId {
		/**
		 * Allows setting contract Id and returns Geozone type for further chaining
		 * 
		 * @param contractId - the contract id
		 * @return Geozone
		 */
		Geozone setContractId(String contractId);
	}

	/**
	 * Geozone interface
	 * 
	 * @author NBhasin
	 *
	 */
	public interface Geozone {
		/**
		 * Allows setting geozone and returns TeamCode type for further chaining
		 * 
		 * @param geozone - the geozone
		 * @return TeamCode
		 */
		TeamCode setGeozone(String geozone);
	}

	/**
	 * TeamCode interface
	 * 
	 * @author NBhasin
	 *
	 */
	public interface TeamCode {
		/**
		 * Allows setting teamCode and returns ProjectCode type for further chaining
		 * 
		 * @param teamCode - the team code
		 * @return ProjectCode
		 */
		ProjectCode setTeamCode(String teamCode);
	}

	/**
	 * ProjectCode interface
	 * 
	 * @author NBhasin
	 *
	 */
	public interface ProjectCode {
		/**
		 * Allows setting projectCode and returns BuildDuration type for further chaining
		 * 
		 * @param projectCode - the project code
		 * @return BuildDuration
		 */
		BuildDuration setProjectCode(String projectCode);
	}

	/**
	 * BuildDuration interface
	 * 
	 * @author NBhasin
	 *
	 */
	public interface BuildDuration {
		/**
		 * Allows setting buildDuration and returns FileRecordCreator type for building the object
		 * 
		 * @param buildDuration - the build duration
		 * @return FileRecordCreator
		 */
		FileRecordCreator setBuildDuration(String buildDuration);
	}

	/**
	 * FileRecordCreator interface
	 * 
	 * @author NBhasin
	 *
	 */
	public interface FileRecordCreator {
		/**
		 * Allows building FileRecord
		 * 
		 * @return FileRecord
		 */
		FileRecord build();
	}
}
