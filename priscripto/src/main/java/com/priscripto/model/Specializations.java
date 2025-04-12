package com.priscripto.model;

import jakarta.persistence.*;

@Entity
@Table(name = "specializations")
public class Specializations {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long specializationId;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private SpecializationType specializationName;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private ServiceType services;

	private Double fees;

	@ManyToOne
	@JoinColumn(name = "doctor_id", nullable = false)
	private Doctors doctor;
	// Getters and Setters

	public Long getSpecializationId() {
		return specializationId;
	}

	public void setSpecializationId(Long specializationId) {
		this.specializationId = specializationId;
	}

	public SpecializationType getSpecializationType() { return specializationName; }
	public void setSpecializationType(SpecializationType specializationName) { this.specializationName = specializationName; }

	public ServiceType getServiceType() { return services; }
	public void setServiceType(ServiceType services) { this.services = services; }

	public Double getFees() { return fees; }
	public void setFees(Double fees) { this.fees = fees; }

	public Doctors getDoctor() { return doctor; }
	public void setDoctor(Doctors doctor) { this.doctor = doctor; }
}
