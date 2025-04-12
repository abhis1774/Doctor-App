package com.priscripto.dto;

import com.priscripto.model.Gender;
import com.priscripto.model.Role;

import java.util.List;

public class DoctorsResponseDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Role role;
    private Long phoneNumber;
    private String profileImage;
    private Gender gender;
    private Integer age;
    private String city;
    private String state;
    private Integer pincode;
    private String country;

    private String clinicAddress;
    private boolean status;
    private List<SpecializationReponseDTO> specialization;  // ✅ Corrected: Now a List
    private List<DegreeReponseDTO> degrees;

    public  DoctorsResponseDTO(Long id,boolean status)
    {
this.id =id;
this.status =status;
    }
    // ✅ Constructor
    public DoctorsResponseDTO(Long id, String firstName, String lastName, String email, Role role, Long phoneNumber,
                              String profileImage, Gender gender, Integer age, String city, String state, Integer pincode,
                              String country, String clinicAddress, List<SpecializationReponseDTO> specialization,
                              List<DegreeReponseDTO> degrees) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        this.phoneNumber = phoneNumber;
        this.profileImage = profileImage;
        this.gender = gender;
        this.age = age;
        this.city = city;
        this.state = state;
        this.pincode = pincode;
        this.country = country;

        this.clinicAddress = clinicAddress;
        this.specialization = specialization;
        this.degrees = degrees;
    }

    //  Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

    public Long getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(Long phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getProfileImage() { return profileImage; }
    public void setProfileImage(String profileImage) { this.profileImage = profileImage; }

    public Gender getGender() { return gender; }
    public void setGender(Gender gender) { this.gender = gender; }

    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public Integer getPincode() { return pincode; }
    public void setPincode(Integer pincode) { this.pincode = pincode; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public String getClinicAddress() { return clinicAddress; }
    public void setClinicAddress(String clinicAddress) { this.clinicAddress = clinicAddress; }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<SpecializationReponseDTO> getSpecialization() { return specialization; }
    public void setSpecialization(List<SpecializationReponseDTO> specialization) { this.specialization = specialization; }

    public List<DegreeReponseDTO> getDegrees() { return degrees; }
    public void setDegrees(List<DegreeReponseDTO> degrees) { this.degrees = degrees; }
}
