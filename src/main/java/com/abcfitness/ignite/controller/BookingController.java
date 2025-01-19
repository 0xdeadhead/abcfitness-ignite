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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/booking")
@RequiredArgsConstructor
public class BookingController {

    private final BookingServiceI bookingService;

    @PostMapping("/")
    @Operation(summary = "Book a class", description = "used by member to register for a class")
    public ResponseEntity<ClassBookingResponseDTO> create(@RequestBody ClassBookingRequestDTO classBookingRequest) {
        Booking booking = bookingService.createBooking(classBookingRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ClassBookingResponseDTO.builder().bookingId(booking.getId()).date(booking.getBookingDate()).build());
    }

    @GetMapping("/")
    @Operation(summary = "retrieve the bookings", description = "used by owner to retrieve the details for the bookings ")
    public ResponseEntity<?> get(
            @Parameter(schema = @Schema(type = "string", format = "date", example = "2025-01-19")) @RequestParam(name = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,

            @Parameter(schema = @Schema(type = "string", format = "date", example = "2025-01-19")) @RequestParam(name = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
            @RequestParam(name = "memberName", required = false) String memberName,
            @RequestParam(name = "ownerId") Long ownerId) {
        return ResponseEntity.ok().body(bookingService.getBookings(ownerId, startDate, endDate, memberName));
    }
}
