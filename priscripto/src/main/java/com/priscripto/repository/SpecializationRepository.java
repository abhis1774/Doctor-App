package com.priscripto.repository;

import com.priscripto.model.Doctors;
import com.priscripto.model.ServiceType;
import com.priscripto.model.SpecializationType;
import com.priscripto.model.Specializations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpecializationRepository extends JpaRepository<Specializations, Long> {
//    List<Doctors> findBySpercializationId(Long SpecializationId);
	Optional<Specializations> findBySpecializationName (SpecializationType specializationName);
	boolean existsBySpecializationNameAndServicesAndDoctor(SpecializationType specializationName, ServiceType services, Doctors doctor);
	//Optional<Specializations> findBySpecializationNameIgnoreCase(SpecializationType specializationName);
}
