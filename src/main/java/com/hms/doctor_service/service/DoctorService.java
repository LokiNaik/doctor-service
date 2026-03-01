package com.hms.doctor_service.service;

import com.hms.doctor_service.entity.Doctor;
import com.hms.doctor_service.dto.DoctorDTO;
import com.hms.doctor_service.dto.DoctorResponseDTO;
import com.hms.doctor_service.exception.DoctorNotFoundException;
import com.hms.doctor_service.repository.DoctorRepo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorService {
    private final DoctorRepo repo;
    private final ModelMapper modelMapper;

    public DoctorService(DoctorRepo repo, ModelMapper mapper) {
        this.repo = repo;
        this.modelMapper = mapper;
    }

    public DoctorResponseDTO createDoctor(DoctorDTO doctorDTO) {
        Doctor doctor = modelMapper.map(doctorDTO, Doctor.class);
        doctor.setCreatedAt(LocalDateTime.now());
        doctor.setDoctorId(generateUserId(doctor.getFirstName(), doctor.getLastName()));
        Doctor savedDoctor = repo.save(doctor);
        return modelMapper.map(savedDoctor, DoctorResponseDTO.class);
    }

    public List<DoctorResponseDTO> getAllDoctors() {
        List<DoctorResponseDTO> doctorResponseDTOList = new ArrayList<>();
        List<Doctor> doctorList = repo.findAll();
        if (doctorList.isEmpty()) {
            throw new DoctorNotFoundException("Doctors not found!");
        }
        doctorList.forEach(doctor -> {
            DoctorResponseDTO responseDTO = modelMapper.map(doctor, DoctorResponseDTO.class);
            doctorResponseDTOList.add(responseDTO);
        });
        return doctorResponseDTOList;
    }

    public DoctorResponseDTO getDoctorByUserId(String doctorId) {
        Doctor byDoctorID = repo.findByDoctorId(doctorId);
        if (byDoctorID == null) {
            throw new DoctorNotFoundException("Doctor with ID: " + doctorId + " is not found!");
        }
        return modelMapper.map(byDoctorID, DoctorResponseDTO.class);
    }

    public DoctorResponseDTO getDoctorByPhone(String phone) {
        Doctor byDoctorPhone = repo.findByPhone(phone);
        if (byDoctorPhone == null) {
            throw new DoctorNotFoundException("Doctor with phone: " + phone + " is not found!");
        }
        return modelMapper.map(byDoctorPhone, DoctorResponseDTO.class);
    }

    private String generateUserId(String firstName, String lastName) {
        if (firstName == null || firstName.isEmpty() ||
                lastName == null || lastName.isEmpty()) {
            throw new IllegalArgumentException("First name and last name cannot be empty");
        }
        firstName = firstName.trim().toUpperCase();
        lastName = lastName.trim().toUpperCase();

        String firstPart = firstName.substring(0, 1);

        String lastPart;
        if (lastName.length() >= 4) {
            lastPart = lastName.substring(0, 4);
        } else {
            lastPart = String.format("%-4s", lastName).replace(' ', 'X');
        }
        return lastPart + firstPart;
    }
}
