package com.priscripto.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DegreeReponseDTO {
	private Long degreeId;
	private String degreeName;
	private LocalDate startDate;
	private LocalDate endDate;
	private String instituteName; // ✅ New field added

	public DegreeReponseDTO(Long degreeId, String degreeName) {
		this.degreeId = degreeId;
		this.degreeName = degreeName;
	}

	public DegreeReponseDTO(Long degreeId, String degreeName, LocalDate startDate, LocalDate endDate, String instituteName) {
		this.degreeId = degreeId;
		this.degreeName = degreeName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.instituteName = instituteName;
	}

	// Getters and Setters
	public Long getDegreeId() {
		return degreeId;
	}

	public void setDegreeId(Long degreeId) {
		this.degreeId = degreeId;
	}

	public String getDegreeName() {
		return degreeName;
	}

	public void setDegreeName(String degreeName) {
		this.degreeName = degreeName;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public String getInstituteName() {
		return instituteName;
	}

	public void setInstituteName(String instituteName) {
		this.instituteName = instituteName;
	}
}
