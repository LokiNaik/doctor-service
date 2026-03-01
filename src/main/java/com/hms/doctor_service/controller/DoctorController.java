package com.hms.doctor_service.controller;

import com.hms.doctor_service.dto.DoctorDTO;
import com.hms.doctor_service.dto.DoctorResponseDTO;
import com.hms.doctor_service.service.DoctorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService){
        this.doctorService = doctorService;
    }

    @PostMapping
    public ResponseEntity<?> createDoctor(@RequestBody DoctorDTO doctor) {
        DoctorResponseDTO dto = doctorService.createDoctor(doctor);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllDoctors() {
        List<DoctorResponseDTO> allDoctors = doctorService.getAllDoctors();
        return new ResponseEntity<>(allDoctors, HttpStatus.OK);
    }

    @GetMapping("/{doctorId}")
    public ResponseEntity<DoctorResponseDTO> getDoctorById(@PathVariable String doctorId){
        DoctorResponseDTO doctorByUserId = doctorService.getDoctorByUserId(doctorId);
        return new ResponseEntity<>(doctorByUserId, HttpStatus.OK);
    }

    @GetMapping("/phone/{mobile}")
    public ResponseEntity<DoctorResponseDTO> getDoctorByPhone(@PathVariable String mobile){
        DoctorResponseDTO doctorByUserId = doctorService.getDoctorByPhone(mobile);
        return new ResponseEntity<>(doctorByUserId, HttpStatus.OK);
    }
}
