package com.priscripto.service;

import com.priscripto.dto.LoginDTO;
import com.priscripto.dto.PatientsDTO;
import com.priscripto.dto.PatientsResponseDTO;
import com.priscripto.dto.RegisterDTO;
import com.priscripto.model.Patients;

import java.util.List;
import java.util.Map;

public interface PatientService {
	void logout(String tokens);
	Patients registerPatient(RegisterDTO registerDTO);
	String loginPatient(LoginDTO loginDTO);
	LoginDTO getPatientDetailsByEmail(String email);
	List<PatientsResponseDTO> getAllPatients();
	PatientsResponseDTO getPatientById(Long id);
	PatientsDTO updatePatient(Long id, PatientsDTO patientsDTO);
	
	// get patient stats
	Map<String, Object>  fetchPatientRegistrationStats();
}
