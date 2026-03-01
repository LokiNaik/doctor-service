package com.hms.doctor_service.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class AvailabilityResponseDTO {
    private String doctorId;
    private LocalDate date;
    private List<String> availableSlots;
}
