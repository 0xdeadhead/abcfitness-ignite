package com.abcfitness.ignite.dto;

import java.time.LocalTime;
import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(type = "string", example = "12:30:00")
    private LocalTime startTime;
    private String memberName;
}
