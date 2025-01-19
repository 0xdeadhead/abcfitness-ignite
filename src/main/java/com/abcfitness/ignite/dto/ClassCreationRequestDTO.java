package com.abcfitness.ignite.dto;

import java.time.LocalTime;
import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClassCreationRequestDTO {
    @NotNull
    @Schema(type = "int",example = "1")
    private Long clubId;
    @NotNull

    @Schema(type = "string", example = "2025-01-19")
    private Date startDate;
    @NotNull

    @Schema(type = "string", example = "2025-01-19")
    private Date endDate;
    @NotNull
    @Min(value = 1)
    private Integer capacity;
    @NotNull
    @Schema(type = "string", example = "12:30:00")
    private LocalTime startTime;
    @NotNull
    private Integer durationInMinutes;
    @NotNull
    @NotEmpty
    private String name;
}
