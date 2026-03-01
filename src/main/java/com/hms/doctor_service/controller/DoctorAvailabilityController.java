package com.hms.doctor_service.controller;

import com.hms.doctor_service.dto.AvailabilityRequestDto;
import com.hms.doctor_service.dto.AvailabilityResponseDTO;
import com.hms.doctor_service.service.DoctorsAvailabilityService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/doctors")
@AllArgsConstructor
public class DoctorAvailabilityController {
    private final DoctorsAvailabilityService service;

    @PostMapping("/availability")
    public ResponseEntity<?> addAvailability(@RequestBody AvailabilityRequestDto requestDto) {

        String availability = service.addAvailability(requestDto);
        return new ResponseEntity<>(availability, HttpStatus.CREATED);
    }

    @GetMapping("/{doctorId}/availability")
    public ResponseEntity<?> getAvailability(@PathVariable String doctorId,@RequestParam LocalDate date){
        AvailabilityResponseDTO availability = service.getAvailability(doctorId, date);
        return new ResponseEntity<>(availability, HttpStatus.OK);

    }
}
