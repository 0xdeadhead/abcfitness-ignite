package com.abcfitness.ignite.dto;

import java.util.Date;

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
    private Date bookingDate;
}
