package com.priscripto.repository;

import com.priscripto.model.DegreeType;
import com.priscripto.model.Degrees;
import com.priscripto.model.Doctors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DegreeRepository extends JpaRepository<Degrees, Long> {
	Optional<Degrees> findByDegreeName(DegreeType degreeName);
	boolean existsByDegreeNameAndDoctor(DegreeType degreeName, Doctors doctor);

}
