package com.Erp_System.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class ApiResponseMessage {
    private String message;
    private boolean success;
    private HttpStatus status;
}
