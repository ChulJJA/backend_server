/*
 * File: NotFoundException.java
 * Project: Moim Back-end
 * Desc: Exception when not found
 * Author: ChulJJA
 * Created: 09.09.2025.
 * Last Modified: 09.09.2025.
 */

package com.moim.exception;

import org.springframework.web.bind.annotation.RestControllerAdvice; // Global controller advice for REST APIs
import org.springframework.web.bind.annotation.ExceptionHandler;     // Marks method to handle specific exceptions
import org.springframework.web.bind.annotation.ResponseStatus;      // Sets HTTP status on the response
import org.springframework.http.HttpStatus; // Enumeration of HTTP status codes
import java.util.Map; // Key-value pairs for JSON response

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> badReq(IllegalArgumentException e) {
        return Map.of("error", e.getMessage());
    }
}
