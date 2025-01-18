package com.abcfitness.ignite.dto;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClassCreationResponseDTO {
    private Long clubClassId;
    private String message;
}
