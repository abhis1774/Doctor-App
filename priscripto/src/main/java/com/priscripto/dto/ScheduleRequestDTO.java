package com.priscripto.dto;

import java.time.LocalDate;
import java.util.List;

public class ScheduleRequestDTO {
    private  Long doctorId;
    private LocalDate scheduleDate;
    private List<TimeSlotRequestDTO> timeSlots;

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public LocalDate getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(LocalDate scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public List<TimeSlotRequestDTO> getTimeSlots() {
        return timeSlots;
    }

    public void setTimeSlots(List<TimeSlotRequestDTO> timeSlots) {
        this.timeSlots = timeSlots;
    }
}
