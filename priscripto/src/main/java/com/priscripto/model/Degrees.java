package com.priscripto.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(  name = "degrees",
        uniqueConstraints = @UniqueConstraint(columnNames = {"degree_name", "doctor_id"}))
public class Degrees {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "degree_id")
    private Long degreeId;

    @Enumerated(EnumType.STRING)
    @Column(name = "degree_name", nullable = false)
    private DegreeType degreeName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(columnDefinition = "TEXT",  name = "institute_name", nullable = false)
    private String instituteName;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctors doctor;

    // Getters and Setters
    public Long getDegreeId() { return degreeId; }
    public void setDegreeId(Long degreeId) { this.degreeId = degreeId; }

    public DegreeType getDegreeName() { return degreeName; }
    public void setDegreeName(DegreeType degreeName) { this.degreeName = degreeName; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public String getInstituteName() { return instituteName; }
    public void setInstituteName(String instituteName) { this.instituteName = instituteName; }

    public Doctors getDoctor() { return doctor; }
    public void setDoctor(Doctors doctor) { this.doctor = doctor; }
}
