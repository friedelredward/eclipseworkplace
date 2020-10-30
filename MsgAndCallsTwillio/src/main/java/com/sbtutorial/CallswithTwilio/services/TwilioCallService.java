package com.sbtutorial.CallswithTwilio.services;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.stereotype.Service;

import com.twilio.rest.api.v2010.account.Call;
import com.twilio.type.PhoneNumber;

@Service
public class TwilioCallService {
	
	public void runCall(String twilioNumber, String targetNumber) throws URISyntaxException {
		Call call=Call.creator(new PhoneNumber(twilioNumber),
				new PhoneNumber(targetNumber),
				URI.create("http://demo.twilio.com/docs/voice.xml"))
				.create();
		System.out.println("App::(CallService):Making call.......");
		System.out.println("Call_id:"+call.getSid());
	}
}
