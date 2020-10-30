package com.sbtutorial.CallswithTwilio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
public class MsgAndCallsTwillioApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsgAndCallsTwillioApplication.class, args);
	}

}
