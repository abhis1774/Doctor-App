package com.priscripto.service;

import com.priscripto.dto.*;
import com.priscripto.model.Degrees;
import com.priscripto.model.Doctors;
import com.priscripto.model.Schedule;
import com.priscripto.model.Specializations;

import java.util.List;
import java.util.Map;

public interface DoctorService {
	void logout(String tokens);
//	doctors interfaces.
	Doctors registerDoctor(RegisterDTO registerDTO);
	String loginDoctor(LoginDTO loginDTO);
	LoginDTO getDoctorDetailsByEmail(String email);
//	Doctors createDoctor(DoctorsRequestDTO doctorsDTO);
	List<DoctorsResponseDTO> getAllDoctors();
	DoctorsResponseDTO getDoctorById(Long id);
	List<DoctorsResponseDTO> getDoctorsBySpecialization(Long SpecializationId);
	List<DoctorsResponseDTO> getDoctorsBySpecializationName(String specializationName);

	Doctors updateDoctor(Long id, DoctorsRequestDTO doctorRequestDTO);
// specialization 	
	Specializations createSpecialization(SpecializationsRequestDTO specializationsDTO);
	List<SpecializationReponseDTO> getAllSpecializationsByIdAndName();
	List<SpecializationReponseDTO> getAllSpecializationsByIdNameService();
	 Map<String, Object> fetchDoctorRegistrationStats();

	Degrees  createDegree(DegreeRequestDTO degreeRequestDTO);
	List<DegreeReponseDTO> getAllDegrees();

	//create schedules.
	Schedule createSchedule(ScheduleRequestDTO scheduleRequestDTO);
	List<ScheduleResponseDTO> getAvailableSchedulesByDoctor(Long doctorId);

}