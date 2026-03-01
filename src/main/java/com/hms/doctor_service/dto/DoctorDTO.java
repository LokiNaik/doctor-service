package com.hms.doctor_service.dto;

import com.hms.doctor_service.entity.Availability;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DoctorDTO {
    private String firstName;
    private String lastName;
    private String specialization;
    private int experience;
    private String phone;
    private Availability status;
}
