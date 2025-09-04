package com.moim.moim_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.moim"})
public class MoimBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoimBackendApplication.class, args);
	}

}
