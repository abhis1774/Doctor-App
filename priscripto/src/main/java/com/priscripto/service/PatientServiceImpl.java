package com.priscripto.service;

import com.priscripto.dto.LoginDTO;
import com.priscripto.dto.PatientsDTO;
import com.priscripto.dto.PatientsResponseDTO;
import com.priscripto.dto.RegisterDTO;
import com.priscripto.exception.AuthenticationException;
import com.priscripto.exception.DuplicateEmailException;
import com.priscripto.model.MedicalHistory;
import com.priscripto.model.Patients;
import com.priscripto.repository.MedicalHistoryRepository;
import com.priscripto.repository.PatientRepository;
import com.priscripto.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements PatientService {

	@Autowired
	PatientRepository patientRepository;
	@Autowired
	MedicalHistoryRepository medicalHistoryRepository;
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	@Autowired
	JwtUtil jwtUtil;
	
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");



	private final Set<String> invalidatedTokens = new HashSet<>();

	@Override
	public Patients registerPatient(RegisterDTO registerDTO) {

		if (patientRepository.findByEmail(registerDTO.getEmail()).isPresent()) {
			throw new DuplicateEmailException("Email Already Exists !");
		}
		Patients patient = new Patients();
		patient.setFirstName(registerDTO.getFirstName());
		patient.setLastName(registerDTO.getLastName());
		patient.setEmail(registerDTO.getEmail());
		patient.setRole(registerDTO.getRole());
		patient.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

		return patientRepository.save(patient);
	}

	 public LoginDTO getPatientDetailsByEmail(String email) {
	        Patients patient = patientRepository.findByEmail(email)
	                .orElseThrow(() -> new UsernameNotFoundException("Patient not found with email: " + email));

	        return new LoginDTO(patient.getId(), patient.getFirstName(),patient.getLastName(),patient.getEmail(), null, patient.getRole());
	    }
	@Override
	public String loginPatient(LoginDTO loginDTO) {
		Optional<Patients> patientLoginDetails = patientRepository.findByEmail(loginDTO.getEmail());
		if (patientLoginDetails.isEmpty() || !passwordEncoder.matches(loginDTO.getPassword(), patientLoginDetails.get().getPassword())) {
			throw new AuthenticationException("Invalid email or password !");
		}
		Patients patient = patientLoginDetails.get();
		return jwtUtil.generateToken(patient.getId(),patient.getEmail(), patient.getRole().toString());
	}

	@Override
	public Map<String, Object>  fetchPatientRegistrationStats() {
		List<Object[]> results = patientRepository.getPatientRegistrationStats();

		List<Long> registrations = new ArrayList<>(Collections.nCopies(12, 0L));
		long totalCount = 0;

		for (Object[] row : results) {
			int monthNumber = ((Number) row[1]).intValue(); // Extract month (1-12)
			long count = ((Number) row[2]).longValue(); // Extract registration count

			registrations.set(monthNumber - 1, count); // Store in correct index
			totalCount += count;
		}

		return Map.of(
				"name", "Patient Registrations",
				"data", registrations,
				"count", totalCount
		);
	}

	public boolean isTokenInvalid(String token) {
		return invalidatedTokens.contains(token);
	}

	@Override
	public void logout(String token) {
		// TODO Auto-generated method stub
		System.out.println(invalidatedTokens);
		invalidatedTokens.add(token);

	}

	@Override
	public PatientsDTO updatePatient(Long id, PatientsDTO patientsDTO) {
		Optional<Patients> patientsOpt = patientRepository.findById(id);
		if (patientsOpt.isPresent()) {
			Patients patients = patientsOpt.get();

			// Updating other fields
			patients.setAge(patientsDTO.getAge());
			patients.setGender(patientsDTO.getGender());
			patients.setBloodGroup(patientsDTO.getBloodGroup());
			patients.setBirthDate(patientsDTO.getBirthDate());
			patients.setCity(patientsDTO.getCity());
			patients.setState(patientsDTO.getState());
			patients.setPincode(patientsDTO.getPincode());
			patients.setCountry(patientsDTO.getCountry());
			patients.setPhone(patientsDTO.getPhone());

			if (patientsDTO.getMedicalHistory() != null) {
				for (MedicalHistory history : patientsDTO.getMedicalHistory()) {
					history.setPatient(patients);
				}
				medicalHistoryRepository.saveAll(patientsDTO.getMedicalHistory());
			}


			patientRepository.save(patients);
			return new PatientsDTO(patients);
		} else {
			throw new RuntimeException("Patient not found with id: " + id);
		}
	}

	@Override
	public List<PatientsResponseDTO> getAllPatients() {
		List<Patients> patients = patientRepository.findAll();
		return patients.stream()
				.map(this::convertToResponseDTO)
				.collect(Collectors.toList());
	}
	@Override
	public PatientsResponseDTO getPatientById(Long id) {
		Optional<Patients> optionalPatient = patientRepository.findById(id);
		if (optionalPatient.isPresent()) {
			return convertToResponseDTO(optionalPatient.get());
		} else {
			throw new RuntimeException("Patient not found with ID: " + id);
		}
	}

	private PatientsResponseDTO convertToResponseDTO(Patients patient) {
		return new PatientsResponseDTO(
				patient.getId(),
				patient.getFirstName(),
				patient.getLastName(),
				patient.getEmail(),
				patient.getPassword(),
				patient.getRole(),
				patient.getAge(),
				patient.getGender(),
				patient.getBloodGroup(),
				patient.getBirthDate(),
				patient.getCity(),
				patient.getState(),
				patient.getPincode(),
				patient.getCountry(),
				patient.getPhone(),
				patient.getMedicalHistory(),
				patient.getCreatedAt(),
				patient.getUpdatedAt()
		);
	}

	

}
