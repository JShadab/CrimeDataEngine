package org.crime.model;

public class CrimeData {

	private String crimeId;
	private String month;
	private String reportedBy;
	private String fallsWithin;
	private String longitude;
	private String latitude;
	private String location;
	private String LSOA_code;
	private String LSOA_name;
	private String crimeType;
	private String lastOutcomeCategory;
	private String Context;

	public String getCrimeId() {
		return crimeId;
	}

	public void setCrimeId(String crimeId) {
		this.crimeId = crimeId;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getReportedBy() {
		return reportedBy;
	}

	public void setReportedBy(String reportedBy) {
		this.reportedBy = reportedBy;
	}

	public String getFallsWithin() {
		return fallsWithin;
	}

	public void setFallsWithin(String fallsWithin) {
		this.fallsWithin = fallsWithin;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLSOA_code() {
		return LSOA_code;
	}

	public void setLSOA_code(String lSOA_code) {
		LSOA_code = lSOA_code;
	}

	public String getLSOA_name() {
		return LSOA_name;
	}

	public void setLSOA_name(String lSOA_name) {
		LSOA_name = lSOA_name;
	}

	public String getCrimeType() {
		return crimeType;
	}

	public void setCrimeType(String crimeType) {
		this.crimeType = crimeType;
	}

	public String getLastOutcomeCategory() {
		return lastOutcomeCategory;
	}

	public void setLastOutcomeCategory(String lastOutcomeCategory) {
		this.lastOutcomeCategory = lastOutcomeCategory;
	}

	public String getContext() {
		return Context;
	}

	public void setContext(String context) {
		Context = context;
	}

	@Override
	public String toString() {
		return "CrimeData [crimeId=" + crimeId + ", month=" + month + ", reportedBy=" + reportedBy + ", fallsWithin="
				+ fallsWithin + ", longitude=" + longitude + ", latitude=" + latitude + ", location=" + location
				+ ", LSOA_code=" + LSOA_code + ", LSOA_name=" + LSOA_name + ", crimeType=" + crimeType
				+ ", lastOutcomeCategory=" + lastOutcomeCategory + ", Context=" + Context + "]";
	}

}
