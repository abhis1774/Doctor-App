package com.priscripto.service;

import com.priscripto.dto.*;
import com.priscripto.dto.SpecializationsRequestDTO;
import com.priscripto.exception.*;
import com.priscripto.model.*;
import com.priscripto.repository.DegreeRepository;
import com.priscripto.repository.DoctorRepository;
import com.priscripto.repository.ScheduleRepository;
import com.priscripto.repository.SpecializationRepository;
import com.priscripto.security.JwtUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DoctorServiceImpl implements DoctorService {
	@Autowired
	DoctorRepository doctorRepository;

	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	JwtUtil  jwtUtil;
	
	@Autowired
    SpecializationRepository specializationRepository;
	
	@Autowired
	DegreeRepository degreeRepository;

	@Autowired
	ScheduleRepository scheduleRepository;
	
	 private final Set<String> invalidatedTokens = new HashSet<>();
	 private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MMM");

	@Override
	public Doctors registerDoctor(RegisterDTO  registerDTO) {
		
		if (doctorRepository.findByEmail(registerDTO.getEmail()).isPresent()) {
			throw new DuplicateEmailException("Email Already Exists !");
		}

	    Doctors doctor = new Doctors();
	    doctor.setFirstName(registerDTO.getFirstName());
	    doctor.setLastName(registerDTO.getLastName());
	    doctor.setEmail(registerDTO.getEmail());
	    doctor.setRole(registerDTO.getRole());
	    doctor.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

		return doctorRepository.save(doctor);
	}

	 public LoginDTO getDoctorDetailsByEmail(String email) {
	        Doctors doctor = doctorRepository.findByEmail(email)
	                .orElseThrow(() -> new UsernameNotFoundException("Doctor not found with email: " + email));

	        return new LoginDTO(doctor.getId(),doctor.getFirstName(),doctor.getLastName(), doctor.getEmail(), null, doctor.getRole());
	    }
	 
	@Override
	public String loginDoctor(LoginDTO loginDTO) {
		Optional<Doctors> doctorLoginDetails = doctorRepository.findByEmail(loginDTO.getEmail());
		if(doctorLoginDetails.isEmpty() ||!passwordEncoder.matches(loginDTO.getPassword(),doctorLoginDetails.get().getPassword())) {
			throw new AuthenticationException("Invalid email or password !");
		}
		Doctors doctor = doctorLoginDetails.get();
		return jwtUtil.generateToken(doctor.getId(),doctor.getEmail(), doctor.getRole().toString());
	}

	// fetches doctor registration statistics
	@Override
	public Map<String, Object> fetchDoctorRegistrationStats() {
	    List<Object[]> results = doctorRepository.getDoctorRegistrationStats();

	    List<Long> registrations = new ArrayList<>(Collections.nCopies(12, 0L));
	    long totalCount = 0;

	    for (Object[] row : results) {
	        int monthNumber = ((Number) row[1]).intValue(); // Extract month (1-12)
	        long count = ((Number) row[2]).longValue(); // Extract registration count

	        registrations.set(monthNumber - 1, count); // Store in correct index
	        totalCount += count;
	    }

	    return Map.of(
	        "name", "Doctor Registrations",
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
	public Specializations createSpecialization(SpecializationsRequestDTO specializationsRequestDTO) {
		// TODO Auto-generated method stub
		if(specializationsRequestDTO.getSpecializationName()==null)
		{
			throw new InvalidTypeException("Specialization cannot be empty !");
		}
		SpecializationType specializationType = SpecializationType.valueOf(specializationsRequestDTO.getSpecializationName().toString());
		ServiceType serviceType = ServiceType.valueOf(String.valueOf(specializationsRequestDTO.getServices()));

		Doctors doctorId = doctorRepository.findById(specializationsRequestDTO.getDoctorId())
				.orElseThrow(() -> new InvalidTypeException("Doctor not found By Id " + specializationsRequestDTO.getDoctorId()));

		if (specializationRepository.existsBySpecializationNameAndServicesAndDoctor(specializationType,serviceType, doctorId)) {
			throw new DuplicateTypeNameException("This degree already exists for the doctor!");
		}

		Doctors doctor = doctorRepository.findById(specializationsRequestDTO.getDoctorId())
				.orElseThrow(()-> new InvalidTypeException("Doctor not found By Id "+specializationsRequestDTO.getDoctorId()));
		Specializations specializations = new Specializations();
		specializations.setSpecializationType(SpecializationType.valueOf(String.valueOf(specializationsRequestDTO.getSpecializationName())));
		specializations.setDoctor(doctor);
		specializations.setServiceType(ServiceType.valueOf(String.valueOf(specializationsRequestDTO.getServices())));
		specializations.setFees(specializationsRequestDTO.getFees());
		return specializationRepository.save(specializations);
	}
	
	@Override
	public List<SpecializationReponseDTO> getAllSpecializationsByIdAndName() {
	    List<Specializations> specializations = specializationRepository.findAll();
	    return specializations.stream()
	            .map(specialization -> new SpecializationReponseDTO(specialization.getSpecializationId(),specialization.getSpecializationType()))
	            .collect(Collectors.toList());
	}

	@Override
	public List<SpecializationReponseDTO> getAllSpecializationsByIdNameService() {
		List<Specializations> specializations = specializationRepository.findAll();
		return specializations.stream()
				.map(specialization -> new SpecializationReponseDTO(specialization.getSpecializationId(),specialization.getSpecializationType(),specialization.getServiceType()))
				.collect(Collectors.toList());
	}



	@Override
	public Degrees createDegree(DegreeRequestDTO degreeRequestDTO) {
		if (degreeRequestDTO.getDegreeName() == null) {
			throw new InvalidTypeException("Degree name cannot be empty!");
		}

		if (degreeRequestDTO.getStartDate() != null && degreeRequestDTO.getEndDate() != null &&
				degreeRequestDTO.getStartDate().isAfter(degreeRequestDTO.getEndDate())) {
			throw new InvalidTypeException("Start date must be before end date.");
		}

		DegreeType degreeType = DegreeType.valueOf(degreeRequestDTO.getDegreeName().toString());

		Doctors doctor = doctorRepository.findById(degreeRequestDTO.getDoctorId())
				.orElseThrow(() -> new InvalidTypeException("Doctor not found By Id " + degreeRequestDTO.getDoctorId()));

		if (degreeRepository.existsByDegreeNameAndDoctor(degreeType, doctor)) {
			throw new DuplicateTypeNameException("This degree already exists for the doctor!");
		}

		Degrees degree = new Degrees();
		degree.setDegreeName(degreeType);
		degree.setDoctor(doctor);
		degree.setStartDate(degreeRequestDTO.getStartDate());
		degree.setEndDate(degreeRequestDTO.getEndDate());
		degree.setInstituteName(degreeRequestDTO.getInstituteName());

		return degreeRepository.save(degree);
	}


	@Override
	public List<DegreeReponseDTO> getAllDegrees() {
		List<Degrees> degrees = degreeRepository.findAll();
		return degrees.stream()
				.map(degree -> new DegreeReponseDTO(degree.getDegreeId(),degree.getDegreeName().toString()))
				.collect(Collectors.toList());
	}

	@Override
	public Schedule createSchedule(ScheduleRequestDTO scheduleRequestDTO) {
   Doctors doctor  =doctorRepository.findById(scheduleRequestDTO.getDoctorId())
		   .orElseThrow(()->new IdNotFoundException("Doctor Id not found."))
		   ;
		DayOfWeek dayOfWeek = scheduleRequestDTO.getScheduleDate().getDayOfWeek();

		Schedule schedule =new Schedule();
		 schedule.setDoctor(doctor);
         schedule.setScheduleDate(scheduleRequestDTO.getScheduleDate());
		 schedule.setDay(com.priscripto.model.DayOfWeek.valueOf(dayOfWeek.toString()));

		System.out.println(com.priscripto.model.DayOfWeek.valueOf(dayOfWeek.toString()));

		List<TimeSlot> timeSlots =new ArrayList<>();

		for(TimeSlotRequestDTO timeSlotRequestDTO:scheduleRequestDTO.getTimeSlots())
		{
            TimeSlot timeSlot = new TimeSlot();
			timeSlot.setStartTime(timeSlotRequestDTO.getStartTime());
			timeSlot.setEndTime(timeSlotRequestDTO.getEndTime());
			timeSlot.setBooked(false);
			timeSlot.setSchedule(schedule);
			timeSlots.add(timeSlot);
		}

		schedule.setTimeSlots(timeSlots);

        return  scheduleRepository.save(schedule);

	}

	@Override
	public List<ScheduleResponseDTO> getAvailableSchedulesByDoctor(Long doctorId) {
		List<Schedule> schedules = scheduleRepository.findAllByDoctorIdWithAvailableSlots(doctorId);

		return schedules.stream().map(schedule -> {
			ScheduleResponseDTO scheduleResponseDTO = new ScheduleResponseDTO();
			scheduleResponseDTO.setScheduleDate(schedule.getScheduleDate());

			List<TimeSlotResponseDTO> availableSlots = schedule.getTimeSlots().stream()
					.filter(slot -> !slot.getBooked())
					.map(slot -> {
						TimeSlotResponseDTO timeSlotResponseDTO = new TimeSlotResponseDTO();
						timeSlotResponseDTO.setTimeSlotId(slot.getId());
						timeSlotResponseDTO.setStartTime(formatTime(slot.getStartTime())); // 👈 Return as AM/PM string
						return timeSlotResponseDTO;
					})
					.collect(Collectors.toList());

			scheduleResponseDTO.setTimeSlots(availableSlots);
			return scheduleResponseDTO;
		}).collect(Collectors.toList());
	}



	private String formatTime(LocalTime time) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		return time.format(formatter); //  Output: 10:30 AM or 05:15 PM
	}

	@Override
	@Transactional
	public Doctors updateDoctor(Long id, DoctorsRequestDTO doctorRequestDTO) {
		Optional<Doctors> optionalDoctor = doctorRepository.findById(id);
		if (optionalDoctor.isEmpty()) {
			throw new RuntimeException("Doctor not found with id: " + id);
		}

		Doctors doctor = optionalDoctor.get();

		// Updating only allowed fields
		if (doctorRequestDTO.getPhoneNumber() != null) {
			doctor.setPhoneNumber(doctorRequestDTO.getPhoneNumber());
		}
		if (doctorRequestDTO.getProfileImage() != null) {
			doctor.setProfileImage(doctorRequestDTO.getProfileImage());
		}
		if (doctorRequestDTO.getGender() != null) {
			doctor.setGender(doctorRequestDTO.getGender());
		}
		if (doctorRequestDTO.getAge() != null) {
			doctor.setAge(doctorRequestDTO.getAge());
		}
		if (doctorRequestDTO.getCity() != null) {
			doctor.setCity(doctorRequestDTO.getCity());
		}
		if (doctorRequestDTO.getState() != null) {
			doctor.setState(doctorRequestDTO.getState());
		}
		if (doctorRequestDTO.getPincode() != null) {
			doctor.setPincode(doctorRequestDTO.getPincode());
		}
		if (doctorRequestDTO.getCountry() != null) {
			doctor.setCountry(doctorRequestDTO.getCountry());
		}

		if (doctorRequestDTO.getClinicAddress() != null) {
			doctor.setClinicAddress(doctorRequestDTO.getClinicAddress());
		}

		// Updating Specialization
		if (doctorRequestDTO.getSpecializationId() != null) {
			Optional<Specializations> specializationOpt = specializationRepository.findById(doctorRequestDTO.getSpecializationId());
			if (specializationOpt.isPresent()) {
				Specializations specialization = specializationOpt.get();
				specialization.setDoctor(doctor);

				List<Specializations> updatedSpecializations = doctor.getSpecializations();
				if (updatedSpecializations == null) {
					updatedSpecializations = new ArrayList<>();
				}

				updatedSpecializations.add(specialization);
				doctor.setSpecializations(updatedSpecializations);
			}
		}



		// Updating Degree
		// Updating Degrees (Single Degree Addition)
		if (doctorRequestDTO.getDegreeId() != null) {
			Optional<Degrees> degree = degreeRepository.findById(doctorRequestDTO.getDegreeId());
			if (degree.isPresent()) {
				// Set the doctor in the degree
				Degrees d = degree.get();
				d.setDoctor(doctor);

				// Add degree to the doctor
				List<Degrees> updatedDegrees = doctor.getDegrees();
				if (updatedDegrees == null) {
					updatedDegrees = new ArrayList<>();
				}
				updatedDegrees.add(d);
				doctor.setDegrees(updatedDegrees);
			}
		}


		return doctorRepository.save(doctor);
	}

	@Override
	public List<DoctorsResponseDTO> getAllDoctors() {
		List<Doctors> doctorsList = doctorRepository.findAll();
		return doctorsList.stream().map(this::convertToDTO).toList();
	}

	@Override
	public DoctorsResponseDTO getDoctorById(Long id) {
		Doctors doctor = doctorRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Doctor not found with ID: " + id));
		return convertToDTO(doctor);
	}


	// Get doctors by specialization
	@Override
	public List<DoctorsResponseDTO> getDoctorsBySpecialization(Long specializationId) {
		List<Doctors> doctors = doctorRepository.findBySpecializations_SpecializationId(specializationId);

		if (doctors.isEmpty()) {
			throw new ResourceNotFoundException("No doctors found with specialization ID: " + specializationId);
		}

		return doctors.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	@Override
	public List<DoctorsResponseDTO> getDoctorsBySpecializationName(String specializationName) {
		Specializations specialization = specializationRepository.findBySpecializationName(SpecializationType.valueOf(specializationName))
				.orElseThrow(() -> new ResourceNotFoundException("Specialization not found with name: " + specializationName));

		List<Doctors> doctors = doctorRepository.findBySpecializations(specialization);

		if (doctors.isEmpty()) {
			throw new ResourceNotFoundException("No doctors found with specialization: " + specializationName);
		}

		return doctors.stream().map(this::convertToDTO).collect(Collectors.toList());
	}



	// Convert Doctors entity to DoctorsResponseDTO
	private DoctorsResponseDTO convertToDTO(Doctors doctor) {

		List<SpecializationReponseDTO> specializationResponseDTOs = null;

		if (doctor.getSpecializations() != null) {
			specializationResponseDTOs = doctor.getSpecializations().stream().map(specialization ->
					new SpecializationReponseDTO(
							specialization.getSpecializationId(),
							specialization.getSpecializationType(),
							specialization.getServiceType(),
							specialization.getFees()
					)
			).toList();
		}


		List<DegreeReponseDTO> degreeReponseDTO = null;

		if (doctor.getDegrees() != null) {
			degreeReponseDTO = doctor.getDegrees().stream().map(degree ->
					new DegreeReponseDTO(
							degree.getDegreeId(),
							degree.getDegreeName().toString(),
							degree.getStartDate(),
							degree.getEndDate(),
							degree.getInstituteName()
					)
			).toList();
		}
		return new DoctorsResponseDTO(
				doctor.getId(),
				doctor.getFirstName(),
				doctor.getLastName(),
				doctor.getEmail(),
				doctor.getRole(),
				doctor.getPhoneNumber(),
				doctor.getProfileImage(),
				doctor.getGender(),
				doctor.getAge(),
				doctor.getCity(),
				doctor.getState(),
				doctor.getPincode(),
				doctor.getCountry(),
				doctor.getClinicAddress(),
				specializationResponseDTOs,
				degreeReponseDTO
		);
	}


}
