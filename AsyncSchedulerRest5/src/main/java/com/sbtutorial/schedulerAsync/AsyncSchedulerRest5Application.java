package com.sbtutorial.schedulerAsync;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAsync
@EnableScheduling
public class AsyncSchedulerRest5Application {

	public static void main(String[] args) {
		SpringApplication.run(AsyncSchedulerRest5Application.class, args);
	}

}
