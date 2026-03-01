package com.hms.doctor_service.dto;

import com.hms.doctor_service.entity.Availability;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class DoctorResponseDTO {
    private Long id;
    private String doctorId;
    private String firstName;
    private String lastName;
    private String specialization;
    private int experience;
    private String phone;
    private Availability status;
    private LocalDateTime createdAt;
}
