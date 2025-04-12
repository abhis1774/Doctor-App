package com.priscripto.dto;

import com.priscripto.model.Gender;

public class DoctorsRequestDTO {

    private Long phoneNumber;
    private String profileImage;
    private Gender gender;
    private Integer age;
    private String city;
    private String state;
    private Integer pincode;
    private String country;
    private Double fees;
    private String clinicAddress;
    private Long specializationId;
    private Long degreeId;

    public DoctorsRequestDTO(Long phoneNumber, String profileImage, Gender gender, Integer age, String city, String state, Integer pincode, String country, Double fees, String clinicAddress, Long specializationId,Long degreeId) {
        this.phoneNumber = phoneNumber;
        this.profileImage = profileImage;
        this.gender = gender;
        this.age = age;
        this.city = city;
        this.state = state;
        this.pincode = pincode;
        this.country = country;
        this.fees = fees;
        this.clinicAddress = clinicAddress;
        this.specializationId = specializationId;
        this.degreeId = degreeId;
    }

    // Default Constructor
    public DoctorsRequestDTO() {
    }

    // Getters and Setters
    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getPincode() {
        return pincode;
    }

    public void setPincode(Integer pincode) {
        this.pincode = pincode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Double getFees() {
        return fees;
    }

    public void setFees(Double fees) {
        this.fees = fees;
    }

    public String getClinicAddress() {
        return clinicAddress;
    }

    public void setClinicAddress(String clinicAddress) {
        this.clinicAddress = clinicAddress;
    }

    public Long getSpecializationId() {
        return specializationId;
    }

    public void setSpecializationId(Long specializationId) {
        this.specializationId = specializationId;
    }

    public Long getDegreeId() {
        return degreeId;
    }

    public void setDegreeId(Long degreeId) {
        this.degreeId = degreeId;
    }
}
