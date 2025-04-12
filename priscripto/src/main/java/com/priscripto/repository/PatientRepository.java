package com.priscripto.repository;

import com.priscripto.model.Patients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patients, Long> {
	Optional<Patients> findByEmail(String email);
	@Query("SELECT YEAR(p.createdAt), MONTH(p.createdAt), COUNT(p.id) FROM Patients p GROUP BY YEAR(p.createdAt), MONTH(p.createdAt) ORDER BY YEAR(p.createdAt), MONTH(p.createdAt)")
	List<Object[]> getPatientRegistrationStats();
}
