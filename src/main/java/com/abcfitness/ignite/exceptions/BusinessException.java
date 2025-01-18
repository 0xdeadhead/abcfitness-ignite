package com.abcfitness.ignite.exceptions;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BusinessException extends RuntimeException {
    private String message;
    private Integer status;
}
