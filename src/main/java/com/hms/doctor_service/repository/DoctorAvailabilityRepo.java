package com.hms.doctor_service.repository;

import com.hms.doctor_service.entity.DoctorAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface DoctorAvailabilityRepo extends JpaRepository<DoctorAvailability, Long> {
    Optional<DoctorAvailability> findByDoctorIdAndAvailableDate(String doctorId, LocalDate date);
}
