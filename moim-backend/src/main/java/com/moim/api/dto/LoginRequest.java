/*
 * File:  LoginRequest.java
 * Project: Moim Back-end
 * Desc:
 * Author: ChulJJA
 * Created: 09.09.2025.
 * Last Modified: 09.09.2025.
 */

package com.moim.api.dto;

public record LoginRequest(String email, String password) {}