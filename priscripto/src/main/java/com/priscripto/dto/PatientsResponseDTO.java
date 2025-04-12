package com.priscripto.dto;

import com.priscripto.model.BloodGroup;
import com.priscripto.model.Gender;
import com.priscripto.model.MedicalHistory;
import com.priscripto.model.Role;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class PatientsResponseDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;
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

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // **Constructor**
    public PatientsResponseDTO(Long id, String firstName, String lastName, String email, String password, Role role,
                               Integer age, Gender gender, BloodGroup bloodGroup, LocalDate birthDate,
                               String city, String state, Integer pincode, String country, Long phone,
                               List<MedicalHistory> medicalHistory,
                               LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.age = age;
        this.gender = gender;
        this.bloodGroup = bloodGroup;
        this.birthDate = birthDate;
        this.city = city;
        this.state = state;
        this.pincode = pincode;
        this.country = country;
        this.phone = phone;
        this.medicalHistory = medicalHistory;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // **Getters & Setters**
    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public Integer getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }

    public BloodGroup getBloodGroup() {
        return bloodGroup;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public Integer getPincode() {
        return pincode;
    }

    public String getCountry() {
        return country;
    }

    public Long getPhone() {
        return phone;
    }

    public List<MedicalHistory> getMedicalHistory() {
        return medicalHistory;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
