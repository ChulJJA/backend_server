/*
 * File: MoimBackendApplication.java
 * Project: Moim Back-end
 * Desc: Main entry point for the Moim Back-end Spring Boot application.
 * Author: ChulJJA
 * Created: 09.01.2025.
 * Last Modified: 09.06.2025.
 */

package com.moim;

import org.springframework.boot.SpringApplication; // Bootstraps and launches the Spring application.
import org.springframework.boot.autoconfigure.SpringBootApplication; // Marks this class as the main entry point for Spring Boot.
//import org.springframework.boot.autoconfigure.domain.EntityScan;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

// Ensures Spring scans all sub-packages under com.moim
@SpringBootApplication(scanBasePackages = {"com.moim"})
//@EnableJpaRepositories(basePackages = "com.moim.repo")
//@EntityScan(basePackages = "com.moim.domain")
public class MoimBackendApplication {
	public static void main(String[] args) {
		SpringApplication.run(MoimBackendApplication.class, args);
	}
}
