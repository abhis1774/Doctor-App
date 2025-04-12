package com.priscripto.dto;

import com.priscripto.model.Role;

public class RegisterDTO {
    private String firstName;
    private String lastName;
    private String email;
    private Role role;
    private String password;
//    private Long specializationId; // Added specialization ID
//    private Long degreeId; // Added degree ID

    // Getters and Setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public Long getSpecializationId() {
//        return specializationId;
//    }
//
//    public void setSpecializationId(Long specializationId) {
//        this.specializationId = specializationId;
//    }
//
//    public Long getDegreeId() {
//        return degreeId;
//    }
//
//    public void setDegreeId(Long degreeId) {
//        this.degreeId = degreeId;
//    }
}
