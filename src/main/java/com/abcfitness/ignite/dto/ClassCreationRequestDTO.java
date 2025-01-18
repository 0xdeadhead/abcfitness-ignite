package com.abcfitness.ignite.dto;

import java.time.LocalTime;
import java.util.Date;

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
    private Long clubId;
    @NotNull
    private Date startDate;
    @NotNull
    private Date endDate;
    @NotNull
    @Min(value = 1)
    private Integer capacity;
    @NotNull
    private LocalTime startTime;
    @NotNull
    private Integer durationInMinutes;
    @NotNull
    @NotEmpty
    private String name;
}
