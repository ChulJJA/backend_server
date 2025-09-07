/*
 * File: MoimBackendApplication.java
 * Project: Moim Back-end
 * Desc: Main entry point for the Moim Back-end Spring Boot application.
 * Author: ChulJJA
 * Created: 09.01.2025.
 * Last Modified: 09.06.2025.
 */

package com.moim.moim_backend;

import org.springframework.boot.SpringApplication; // Bootstraps and launches the Spring application.
import org.springframework.boot.autoconfigure.SpringBootApplication; // Marks this class as the main entry point for Spring Boot.

                    // Ensures Spring scans all sub-packages under com.moim
@SpringBootApplication(scanBasePackages = {"com.moim"})
public class MoimBackendApplication {
	public static void main(String[] args) {
		SpringApplication.run(MoimBackendApplication.class, args);
	}
}
