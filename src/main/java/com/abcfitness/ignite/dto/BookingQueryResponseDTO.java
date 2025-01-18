package com.abcfitness.ignite.dto;

import java.time.LocalTime;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingQueryResponseDTO {
    private String className;
    private Date bookingDate;
    private LocalTime startTime;
    private String memberName;
}
