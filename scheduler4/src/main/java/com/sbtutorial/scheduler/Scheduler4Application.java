package com.sbtutorial.scheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Scheduler4Application {

	public static void main(String[] args) {
		SpringApplication.run(Scheduler4Application.class, args);
	}

}
