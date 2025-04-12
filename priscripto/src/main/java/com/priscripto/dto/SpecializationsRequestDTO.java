package com.priscripto.dto;

import com.priscripto.model.ServiceType;
import com.priscripto.model.SpecializationType;

public class SpecializationsRequestDTO {


	private SpecializationType specializationName;

	private Long doctorId;


	private ServiceType services;


	private Double fees;

	public SpecializationType getSpecializationName() {
		return specializationName;
	}

	public void setSpecializationName(SpecializationType specializationName) {
		this.specializationName = specializationName;
	}

	public Long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}

	public ServiceType getServices() {
		return services;
	}

	public void setServices(ServiceType services) {
		this.services = services;
	}

	public Double getFees() {
		return fees;
	}

	public void setFees(Double fees) {
		this.fees = fees;
	}
}
