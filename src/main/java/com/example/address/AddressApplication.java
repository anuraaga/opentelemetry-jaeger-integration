package com.example.address;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.opentelemetry.api.OpenTelemetry;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;

@SpringBootApplication
@OpenAPIDefinition
public class AddressApplication {

	public static void main(String[] args) {

		SpringApplication.run(AddressApplication.class, args);
	}

}
