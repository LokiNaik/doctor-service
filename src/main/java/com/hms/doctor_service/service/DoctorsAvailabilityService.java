package com.hms.doctor_service.service;

import com.hms.doctor_service.dto.AvailabilityRequestDto;
import com.hms.doctor_service.dto.AvailabilityResponseDTO;
import com.hms.doctor_service.entity.DoctorAvailability;
import com.hms.doctor_service.repository.DoctorAvailabilityRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class DoctorsAvailabilityService {

    private final DoctorAvailabilityRepo availabilityRepo;

    public String addAvailability(AvailabilityRequestDto requestDto) {
        log.info("Adding availability for the doctor {} date {}", requestDto.getDoctorId(), requestDto.getAvailableDate());
        DoctorAvailability availability = new DoctorAvailability();

        availability.setDoctorId(requestDto.getDoctorId());
        availability.setAvailableDate(requestDto.getAvailableDate());
        availability.setStartTime(requestDto.getStartTime());
        availability.setEndTime(requestDto.getEndTime());
        availability.setSlotDuration(requestDto.getSlotDuration());

        Optional<DoctorAvailability> byDoctorIdAndAvailableDate = findAvailabilityByDoctorIdAndAvailableDate(availability.getDoctorId(), availability.getAvailableDate());
        if (byDoctorIdAndAvailableDate.isPresent()) {
            log.warn("Slots already updated for the day {}", availability.getAvailableDate());
            return "Slot already booked for the date " + availability.getAvailableDate();
        }
        availabilityRepo.save(availability);
        return "Slot created";
    }

    public AvailabilityResponseDTO getAvailability(String doctorId, LocalDate date) {
        log.info("Fetching the available slots for the doctor {} on {}", doctorId, date);
        DoctorAvailability byDoctorIdAndAvailableDate = findAvailabilityByDoctorIdAndAvailableDate(doctorId, date)
                .orElseThrow(() -> new RuntimeException("Availability not found for the date "+date));
        List<String> slots = generateSlots(byDoctorIdAndAvailableDate);

        AvailabilityResponseDTO response = new AvailabilityResponseDTO();

        response.setDoctorId(doctorId);
        response.setDate(date);
        response.setAvailableSlots(slots);

        return response;
    }

    private Optional<DoctorAvailability> findAvailabilityByDoctorIdAndAvailableDate(String doctorId, LocalDate date) {
        return availabilityRepo.findByDoctorIdAndAvailableDate(doctorId, date);
    }

    private List<String> generateSlots(DoctorAvailability availability) {
        log.info("Generating slots based on the slot time..");
        List<String> slots = new ArrayList<>();

        LocalTime start = availability.getStartTime();
        LocalTime end = availability.getEndTime();

        while (start.isBefore(end)) {

            slots.add(start.toString());

            start = start.plusMinutes(
                    availability.getSlotDuration());
        }
        log.info("Generated slots...");
        return slots;
    }
}
