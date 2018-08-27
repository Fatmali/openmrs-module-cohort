package org.openmrs.module.cohort;

import java.util.Date;
import org.openmrs.BaseOpenmrsData;
import org.openmrs.Location;
import org.openmrs.Program;
import org.openmrs.Provider;

public class CohortM extends BaseOpenmrsData {
	
	private static final long serialVersionUID = 1L;
	
	private Integer cohortId;
	private String name;
	private String description;
	private Location location;
	private Date startDate;
	private Date endDate;
	private CohortType cohortType;
	private CohortProgram cohortProgram;
	private Boolean groupCohort;
	private String groupNumber;
	private String landmark;
	private Provider provider;
	private Program program;

	public Program getProgram() {
		return program;
	}

	public void setProgram(Program program) {
		this.program = program;
	}

	public String getGroupNumber() {
		return groupNumber;
	}

	public void setGroupNumber(String groupNumber) {
		this.groupNumber = groupNumber;
	}

	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	public Integer getCohortId() {
		return cohortId;
	}

	public void setCohortId(Integer cohortId) {
		this.cohortId = cohortId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public CohortType getCohortType() {
		return cohortType;
	}

	public void setCohortType(CohortType cohortType) {
		this.cohortType = cohortType;
	}

	public CohortProgram getCohortProgram() {
		return cohortProgram;
	}

	public void setCohortProgram(CohortProgram cohortProgram) {
		this.cohortProgram = cohortProgram;
	}

	public Boolean isGroupCohort() {
		return groupCohort;
	}

	public void setGroupCohort(Boolean groupCohort) {
		this.groupCohort = groupCohort;
	}

	public Boolean getGroupCohort() { return this.groupCohort; }

	@Override
	public Integer getId() {
		return getCohortId();
	}
	
	@Override
	public void setId(Integer id) {
		setCohortId(id);
	}
	
	@Override
	public String toString() {
		return this.name;
	}
}
	
	
	
