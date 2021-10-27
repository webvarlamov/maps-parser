package com.webvarlamov.leafletparser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class LeafletparserApplication {

	public static void main(String[] args) {
		SpringApplication.run(LeafletparserApplication.class, args);
	}

}
