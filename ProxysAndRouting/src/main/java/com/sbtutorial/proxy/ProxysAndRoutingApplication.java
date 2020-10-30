package com.sbtutorial.proxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class ProxysAndRoutingApplication {

	@RequestMapping(value="/endpoint1")
	public String enpoint1() {
		return "<h2 style='color:tomato'>Hellow from enpoint1</h2>";
	}
	
	@RequestMapping(value="/endpoint2")
	public String enpoint2() {
		return "<h2 style='color :violet'>Hellow from enpoint2</h2>";
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ProxysAndRoutingApplication.class, args);
	}

}
