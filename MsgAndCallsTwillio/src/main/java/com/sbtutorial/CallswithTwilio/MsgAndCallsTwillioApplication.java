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
public class MsgAndCallsTwillioApplication implements ApplicationRunner {
	private static final String T_ID="ACfa78a6e5b3e391a3431d8272c237b614";
	private static final String T_AUTH_ID="71e198cff68ba3982508ebbb2dd9f1a6";
	public static final String T_NUMBER="+16502065490";
	

	public static void main(String[] args) {
		SpringApplication.run(MsgAndCallsTwillioApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Twilio.init(T_ID, T_AUTH_ID);
		Call call=Call.creator(new PhoneNumber("+34601371747"),
				new PhoneNumber(T_NUMBER),
				URI.create("http://demo.twilio.com/docs/voice.xml"))
				.create();
		System.out.println("App::(CallService):Making call.......");
		System.out.println("Call_id:"+call.getSid());
		
	}

}
