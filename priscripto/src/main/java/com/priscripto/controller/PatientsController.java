package com.priscripto.controller;

import com.priscripto.dto.PatientsDTO;
import com.priscripto.dto.PatientsResponseDTO;
import com.priscripto.service.PatientService;
import com.priscripto.service.PatientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/")
public class PatientsController {
    @Autowired
    private PatientServiceImpl patientServiceImpl;

    @Autowired
    private PatientService patientService;

    @GetMapping("/v1/patient/getAllPatients")
// @PreAuthorize("hasAuthority('DOCTOR')")
    public ResponseEntity<List<PatientsResponseDTO>> getAllPatients() {
        List<PatientsResponseDTO> patients = patientServiceImpl.getAllPatients();
        return ResponseEntity.ok(patients);
    }

    @GetMapping("/v1/patient/getPatient/{id}")
    public ResponseEntity<PatientsResponseDTO> getPatientById(@PathVariable Long id) {
        PatientsResponseDTO patient = patientService.getPatientById(id);
        return ResponseEntity.ok(patient);
    }



    @PutMapping("/v1/patient/update/{id}")
  // @PreAuthorize("hasAuthority('PATIENT')")
    public ResponseEntity<String> updatePatient(@PathVariable Long id, @RequestBody PatientsDTO patientsDTO) {
        patientServiceImpl.updatePatient(id, patientsDTO);
        return ResponseEntity.status(HttpStatus.OK).body("Patient details updated successfully!");
    }
    
    @GetMapping("/v1/patient/patient-register-stats")
    public ResponseEntity<Map<String, Object>> getPatientRegistrationStats() {
        Map<String, Object> stats = patientServiceImpl.fetchPatientRegistrationStats();

        if (((List<?>) stats.get("data")).isEmpty()) {
            return ResponseEntity.ok(Map.of("message", "No registration data available"));
        }

        return ResponseEntity.ok(stats);
    }
}
