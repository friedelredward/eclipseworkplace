package com.sbtutorial.CallswithTwilio.services;

import org.springframework.stereotype.Service;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class TwilioSMSService {
	
	public void runSMS(String twilioNumber, String targetNumber) {
		Message.creator(new PhoneNumber(targetNumber),
					new PhoneNumber(twilioNumber),
					"Hellow desde twilio sDK spring boot Redwardweb :D"
				).create();
		System.out.println("App::(SmsService):Sending meesage.......");
	}
}
