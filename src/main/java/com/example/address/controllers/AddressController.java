package com.example.address.controllers;

import io.opentelemetry.api.OpenTelemetry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.address.models.Address;
import com.example.address.services.AddressService;

import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;

@RestController
public class AddressController {

	private final Tracer tracer;
	
	@Autowired
	AddressService addressService;

	@Autowired
	public AddressController(OpenTelemetry openTelemetry) {
		this.tracer = openTelemetry.getTracer("app");
	}

	@GetMapping("/address/{userId}")
	  public Address getAddress(@PathVariable long userId) { 
		  Span span = tracer.spanBuilder("userSpan").startSpan();
		  Address address = addressService.getAddress(userId); 
		  span.end(); 
		  return address;
	  
	  }
	 
	
	/*
	 * @GetMapping("/address/{userId}") public Address getAddress(@PathVariable long
	 * userId) { return addressService.getAddress(userId);
	 * 
	 * 
	 * }
	 */
	
	@PostMapping("/address")
	public Address getAddress(@RequestBody Address address) {
		return addressService.saveAddress(address);
	}
	
	@PutMapping("address/{userId}")
	public Address getAddress(@RequestBody Address address, @PathVariable long userId) {
		return addressService.saveAddress(address);
	}

}
