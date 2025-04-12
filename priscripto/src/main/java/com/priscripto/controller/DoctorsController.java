package com.priscripto.controller;

import com.priscripto.dto.*;
import com.priscripto.model.Doctors;
import com.priscripto.repository.DegreeRepository;
import com.priscripto.service.DoctorService;
import com.priscripto.service.DoctorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/")
public class DoctorsController {
    @Autowired
    private DoctorServiceImpl doctorServiceImpl;
    @Autowired
    DoctorService doctorService;
    
    @Autowired
    DegreeRepository degreeRepository;



    @PutMapping("/v1/doctor/update/{id}")
    public ResponseEntity<Doctors> updateDoctor(
            @PathVariable Long id,
            @RequestBody DoctorsRequestDTO doctorRequestDTO) {
        Doctors updatedDoctor = doctorService.updateDoctor(id, doctorRequestDTO);
        return ResponseEntity.ok(updatedDoctor);
    }

    // Get all doctors
    @GetMapping("/v1/doctors")
    public ResponseEntity<List<DoctorsResponseDTO>> getAllDoctors() {
        List<DoctorsResponseDTO> doctors = doctorServiceImpl.getAllDoctors();
        return ResponseEntity.ok(doctors);
    }

    // Get doctor by ID
    @GetMapping("/v1/doctor/{id}")
    public ResponseEntity<DoctorsResponseDTO> getDoctorById(@PathVariable Long id) {
        DoctorsResponseDTO doctor = doctorServiceImpl.getDoctorById(id);
        return ResponseEntity.ok(doctor);
    }


    // Get doctors by specialization ID
    @GetMapping("/v1/doctors/specialization/{specializationId}")
    public ResponseEntity<List<DoctorsResponseDTO>> getDoctorsBySpecialization(@PathVariable Long specializationId) {
        List<DoctorsResponseDTO> doctors = doctorServiceImpl.getDoctorsBySpecialization(specializationId);
        return ResponseEntity.ok(doctors);
    }



    @PostMapping("/v1/doctors/add-specialization")
    public ResponseEntity<String> addSpecialization(@RequestBody SpecializationsRequestDTO specializationsDTO){
        doctorServiceImpl.createSpecialization(specializationsDTO);
        return ResponseEntity.ok(specializationsDTO.getSpecializationName()+" Specialization Added !");

    }

    // Get doctors by specialization name
    @GetMapping("/v1/doctors/specialization/name/{specializationName}")
    public ResponseEntity<List<DoctorsResponseDTO>> getDoctorsBySpecializationName(@PathVariable String specializationName) {
        List<DoctorsResponseDTO> doctors = doctorServiceImpl.getDoctorsBySpecializationName(specializationName);
        return ResponseEntity.ok(doctors);
    }



    @PostMapping("/v1/doctors/specialization")
    public ResponseEntity<String> createSpecialization(@RequestBody SpecializationsRequestDTO specializationsDTO)

    {
        doctorServiceImpl.createSpecialization(specializationsDTO);
        return ResponseEntity.ok(specializationsDTO.getSpecializationName()+" Specialization Added !");
    }
    
    @GetMapping("/v1/doctors/getSpecName")
    public ResponseEntity<List<SpecializationReponseDTO>> getAllSpecializationsByIdAndName() {
        List<SpecializationReponseDTO> specializations = doctorServiceImpl.getAllSpecializationsByIdAndName();
        return ResponseEntity.ok(specializations);
    }

    @GetMapping("/v1/doctors/getSpecNameService")
    public ResponseEntity<List<SpecializationReponseDTO>> getAllSpecializationsByIdNameService() {
        List<SpecializationReponseDTO> specializations = doctorServiceImpl.getAllSpecializationsByIdNameService();
        return ResponseEntity.ok(specializations);
    }

    @GetMapping("/v1/doctors/degrees")
    public ResponseEntity<List<DegreeReponseDTO>> getAllDegrees() {
        List<DegreeReponseDTO> degrees = doctorServiceImpl.getAllDegrees();
        return ResponseEntity.ok(degrees);
    }


    @PostMapping("/v1/doctors/add-degree")
    public ResponseEntity<String> addDegree(@RequestBody DegreeRequestDTO degreeRequestDTO)
    {

        doctorServiceImpl.createDegree(degreeRequestDTO);
       // System.out.println(degreeRequestDTO.getDegreeId());
        return ResponseEntity.ok(degreeRequestDTO.getDegreeName()+" Degree Added !");
    }
    
    @GetMapping("/v1/doctors/doctor-register-stats")
    public ResponseEntity<Map<String, Object>> getDoctorRegistrationStats() {
        Map<String, Object> stats = doctorService.fetchDoctorRegistrationStats();

        if (((List<?>) stats.get("data")).isEmpty()) {
            return ResponseEntity.ok(Map.of("message", "No registration data available"));
        }

        return ResponseEntity.ok(stats);
    }


    @PostMapping("/v1/doctors/schedule/create")
    public  ResponseEntity<?> addSchedule(@RequestBody ScheduleRequestDTO scheduleRequestDTO)
    {
        doctorServiceImpl.createSchedule(scheduleRequestDTO);
        return ResponseEntity.ok("Schedule Added !");
    }

    @GetMapping("/v1/doctors/{doctorId}/available-schedules")
    public ResponseEntity<List<ScheduleResponseDTO>> getAvailableSchedules(
            @PathVariable Long doctorId) {
        List<ScheduleResponseDTO> schedules = doctorServiceImpl.getAvailableSchedulesByDoctor(doctorId);
        return ResponseEntity.ok(schedules);
    }
}
