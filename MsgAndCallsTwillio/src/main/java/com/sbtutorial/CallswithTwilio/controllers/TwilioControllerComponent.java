package com.sbtutorial.CallswithTwilio.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.sbtutorial.CallswithTwilio.services.TwilioCallService;
import com.sbtutorial.CallswithTwilio.services.TwilioSMSService;
import com.twilio.Twilio;

@Component
public class TwilioControllerComponent implements CommandLineRunner {
	private static final Logger logger= LoggerFactory.getLogger(TwilioControllerComponent.class);
	private static final String T_ID="ACfa78a6e5b3e391a3431d8272c237b614";
	private static final String T_AUTH_ID="83687fb89de0f541b7012f10dfb9cbfb";
	public static final String T_NUMBER="+16502065490";
	//+15046845213
	
	static {
		Twilio.init(T_ID, T_AUTH_ID);
	}
	
	@Autowired
	private TwilioSMSService smsService;
	
	@Override
	public void run(String... args) throws Exception {
			logger.info("Twilliocontroller started:init::->OK");
//			smsService.runSMS(T_NUMBER, "+34601371747");
//			logger.info("App:Controller::finished sending...");
		
	}
	

}
