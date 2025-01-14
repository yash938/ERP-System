package com.Erp_System.response;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
@Data
@Builder
public class Utils {

    private String message;
    private HttpStatus status;
    private LocalDate date;
}
