package com.priscripto.repository;

import com.priscripto.model.Doctors;
import com.priscripto.model.Specializations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctors,Long> {
	 Optional<Doctors> findByEmail(String email);
	List<Doctors> findBySpecializations_SpecializationId(Long specializationId);
	List<Doctors> findBySpecializations(Specializations specialization);
	 
	 // doctors registration count based on Month.
	@Query("SELECT YEAR(d.createdAt), MONTH(d.createdAt), COUNT(d.id) FROM Doctors d GROUP BY YEAR(d.createdAt), MONTH(d.createdAt) ORDER BY YEAR(d.createdAt), MONTH(d.createdAt)")
	List<Object[]> getDoctorRegistrationStats();



}

