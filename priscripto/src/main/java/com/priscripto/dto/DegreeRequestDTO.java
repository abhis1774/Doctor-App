package com.priscripto.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.priscripto.model.DegreeType;

import java.time.LocalDate;

public class DegreeRequestDTO {
    private Long degreeId;
    private DegreeType degreeType;
    private Long doctorId;              // ✅ Doctor ID present
    private String instituteName;       // ✅ Institute name added

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate startDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate endDate;

    // Constructors
    public DegreeRequestDTO() {
    }

    public DegreeRequestDTO(Long degreeId, DegreeType degreeType, Long doctorId,
                            String instituteName, LocalDate startDate, LocalDate endDate) {
        this.degreeId = degreeId;
        this.degreeType = degreeType;
        this.doctorId = doctorId;
        this.instituteName = instituteName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Getters and Setters
    public Long getDegreeId() {
        return degreeId;
    }

    public void setDegreeId(Long degreeId) {
        this.degreeId = degreeId;
    }

    public DegreeType getDegreeName() {
        return degreeType;
    }

    public void setDegreeName(DegreeType degreeType) {
        this.degreeType = degreeType;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public String getInstituteName() {
        return instituteName;
    }

    public void setInstituteName(String instituteName) {
        this.instituteName = instituteName;
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
}
