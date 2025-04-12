package com.priscripto.repository;

import com.priscripto.model.MedicalHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalHistoryRepository extends JpaRepository<MedicalHistory, Long> {

    // Find all medical history records for a specific patient
    List<MedicalHistory> findByPatientId(Long patientId);
}
