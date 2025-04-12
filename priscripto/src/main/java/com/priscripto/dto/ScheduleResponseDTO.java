package com.priscripto.dto;

import java.time.LocalDate;
import java.util.List;

public class ScheduleResponseDTO {
    private LocalDate scheduleDate;
    private List<TimeSlotResponseDTO> timeSlots;

    public LocalDate getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(LocalDate scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public List<TimeSlotResponseDTO> getTimeSlots() {
        return timeSlots;
    }

    public void setTimeSlots(List<TimeSlotResponseDTO> timeSlots) {
        this.timeSlots = timeSlots;
    }
}
