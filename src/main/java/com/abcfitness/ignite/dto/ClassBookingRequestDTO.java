package com.abcfitness.ignite.dto;

import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClassBookingRequestDTO {
    private Long memberId;
    private Long classId;
    @Schema(type = "string", example = "2025-01-19")
    private Date bookingDate;
}
