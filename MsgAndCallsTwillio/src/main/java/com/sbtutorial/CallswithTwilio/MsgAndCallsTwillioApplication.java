package com.sbtutorial.CallswithTwilio;

import java.net.URI;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.type.PhoneNumber;

@SpringBootApplication
public class MsgAndCallsTwillioApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsgAndCallsTwillioApplication.class, args);
	}

}
