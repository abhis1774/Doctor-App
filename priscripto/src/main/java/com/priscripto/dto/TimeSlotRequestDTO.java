package com.priscripto.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.priscripto.util.AmPmTimeDeserializer;

import java.time.LocalTime;

public class TimeSlotRequestDTO {
private  Long id;

@JsonDeserialize(using = AmPmTimeDeserializer.class)
@JsonFormat(pattern = "HH:mm")
private LocalTime startTime;

@JsonDeserialize(using = AmPmTimeDeserializer.class)
@JsonFormat(pattern = "HH:mm")
private LocalTime endTime;

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }
}
