package com.priscripto.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.priscripto.model.ServiceType;
import com.priscripto.model.SpecializationType;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SpecializationReponseDTO {
    private Long specializationId;
    private SpecializationType specializationName;
    private ServiceType services;
    private Double fees;

    public SpecializationReponseDTO(Long specializationId, SpecializationType specializationName) {
  this.specializationId =specializationId;
  this.specializationName =specializationName;
    }

    public SpecializationReponseDTO(Long specializationId, SpecializationType specializationName, ServiceType services) {
    this.specializationId =specializationId;
    this.specializationName = specializationName;
    this.services =services;
    }

    public SpecializationReponseDTO(Long specializationId, SpecializationType specializationName, ServiceType services,Double fees) {
        this.specializationId =specializationId;
        this.specializationName = specializationName;
        this.services =services;
        this.fees =fees;
    }
    public Long getSpecializationId() {
        return specializationId;
    }

    public void setSpecializationId(Long specializationId) {
        this.specializationId = specializationId;
    }

    public SpecializationType getSpecializationName() {
        return specializationName;
    }

    public void setSpecializationName(SpecializationType specializationName) {
        this.specializationName = specializationName;
    }

    public ServiceType getServices() {
        return services;
    }

    public Double getFees() {
        return fees;
    }

    public void setFees(Double fees) {
        this.fees = fees;
    }

    public void setServices(ServiceType services) {
        this.services = services;
    }


}
