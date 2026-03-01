package com.hms.doctor_service.repository;

import com.hms.doctor_service.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepo extends JpaRepository<Doctor, Long> {
    Doctor findByDoctorId(String doctorId);
    Doctor findByPhone(String phone);
}
