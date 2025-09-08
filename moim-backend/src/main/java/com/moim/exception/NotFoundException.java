/*
 * File: NotFoundException.java
 * Project: Moim Back-end
 * Desc: Exception when not found
 * Author: ChulJJA
 * Created: 09.07.2025.
 * Last Modified: 09.07.2025.
 */

package com.moim.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
    public NotFoundException(String what) { super(what + " not found"); }
}