/*
 * File: NotFoundException.java
 * Project: Moim Back-end
 * Desc: Exception when not found
 * Author: ChulJJA
 * Created: 09.07.2025.
 * Last Modified: 09.08.2025.
 */

package com.moim.exception;

import org.springframework.http.HttpStatus; // Enumeration of HTTP status codes
import org.springframework.web.bind.annotation.ResponseStatus; // Maps exceptions to specific HTTP status codes

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
    public NotFoundException(String what) { super(what + " not found"); }
}