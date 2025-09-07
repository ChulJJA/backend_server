/*
 * File: PingController.java
 * Project: Moim Back-end
 * Desc: Endpoint health check controller.
 * Author: ChulJJA
 * Created: 09.01.2025.
 * Last Modified: 09.06.2025.
 */

package com.moim.api;

import org.springframework.web.bind.annotation.GetMapping; // Maps HTTP GET requests to a handler method.
import org.springframework.web.bind.annotation.RestController; // Spring REST controller, handle HTTP requests.
import java.util.Map; // Return key-value pairs in JSON response format.

@RestController
public class PingController {
    @GetMapping("/ping")
    // Health check endpoint at "ping" request.
    public Map<String, String> ping() {
        return Map.of("ok", "true", "service", "moim-backend");
    }
}
