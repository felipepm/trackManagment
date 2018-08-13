package com.pm.trackManagment.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeRestController {

	@GetMapping("/")
	public String init() {
		return "Welcome to Track Managment System!";
	}
}
