package com.priscripto.repository;

import com.priscripto.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    @Query("SELECT ds FROM Schedule ds JOIN FETCH ds.timeSlots ts WHERE ds.doctor.id = :doctorId AND ts.isBooked = false")
    List<Schedule> findAllByDoctorIdWithAvailableSlots(@Param("doctorId") Long doctorId);


}
