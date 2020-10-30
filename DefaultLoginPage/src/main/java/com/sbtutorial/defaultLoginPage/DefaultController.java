package com.sbtutorial.defaultLoginPage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultController {

	@GetMapping("/")
	public String defaultHome() {
		
		return"<h1>Welcome to Home / </h1>";
	}

}
