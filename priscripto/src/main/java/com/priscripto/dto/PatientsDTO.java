package com.priscripto.dto;

import com.priscripto.model.BloodGroup;
import com.priscripto.model.Gender;
import com.priscripto.model.MedicalHistory;
import com.priscripto.model.Patients;

import java.time.LocalDate;
import java.util.List;

public class PatientsDTO {
    private Integer age;
    private Gender gender;
    private BloodGroup bloodGroup;
    private LocalDate birthDate;
    private String city;
    private String state;
    private Integer pincode;
    private String country;
    private Long phone;
    private List<MedicalHistory> medicalHistory;

    //    // Constructor to convert Patients entity to DTO
    public PatientsDTO(Patients patients) {
        this.age = patients.getAge();
        this.gender = patients.getGender();
        this.bloodGroup = patients.getBloodGroup();
        this.birthDate = patients.getBirthDate();
        this.city = patients.getCity();
        this.state = patients.getState();
        this.pincode = patients.getPincode();
        this.country = patients.getCountry();
        this.phone = patients.getPhone();
        this.medicalHistory = patients.getMedicalHistory();
    }
    public PatientsDTO() {}

//    public PatientsDTO(Long id, String firstName, String lastName, String email, Integer age, Gender gender, BloodGroup bloodGroup, LocalDate birthDate, String city, String state, Integer pincode, String country, Long phone) {
//    }

    // Getters and Setters
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public BloodGroup getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(BloodGroup bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
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

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public List<MedicalHistory> getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(List<MedicalHistory> medicalHistory) {
        this.medicalHistory = medicalHistory;
    }
}
