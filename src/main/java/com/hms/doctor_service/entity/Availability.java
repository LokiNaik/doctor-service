package com.hms.doctor_service.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;


@Entity
@Table(name = "DOCTOR-AVAILABILITY")
@Getter
@Setter
public class Availability {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "DOCTORS_ID")
    private String doctorId;
    private LocalDateTime availableDate;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int maxToken;
}
