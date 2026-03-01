package com.hms.doctor_service.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "DOCTORS")
@Getter
@Setter
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column( unique = true)
    private String doctorId;
    private String firstName;
    private String lastName;
    private String specialization;
    private int experience;
    @Column(unique = true)
    private String phone;
    private LocalDateTime createdAt;
//    @OneToOne(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonManagedReference
//    private Availability status;

//    private LocalDateTime availableDate;
//    private LocalDateTime startTime;
//    private LocalDateTime endTime;
//    private int maxToken;
}
