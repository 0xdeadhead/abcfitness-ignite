package com.abcfitness.ignite.controller;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abcfitness.ignite.dto.ClassBookingRequestDTO;
import com.abcfitness.ignite.dto.ClassBookingResponseDTO;
import com.abcfitness.ignite.entity.Booking;
import com.abcfitness.ignite.service.BookingServiceI;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/booking")
@RequiredArgsConstructor
public class BookingController {

    private final BookingServiceI bookingService;

    @PostMapping("/")
    public ResponseEntity<ClassBookingResponseDTO> create(@RequestBody ClassBookingRequestDTO classBookingRequest) {
        Booking booking = bookingService.createBooking(classBookingRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ClassBookingResponseDTO.builder().bookingId(booking.getId()).date(booking.getBookingDate()).build());
    }

    @GetMapping("/")
    public ResponseEntity<?> get(
            @RequestParam(name = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam(name = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
            @RequestParam(name = "memberName", required = false) String memberName,
            @RequestParam(name = "ownerId") Long ownerId) {
        return ResponseEntity.ok().body(bookingService.getBookings(ownerId, startDate, endDate, memberName));
    }
}
