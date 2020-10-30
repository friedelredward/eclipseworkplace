package com.sbtutorial.CallswithTwilio.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.sbtutorial.CallswithTwilio.services.TwilioCallService;
import com.sbtutorial.CallswithTwilio.services.TwilioSMSService;
import com.twilio.Twilio;

@Component
public class TwilioControllerComponent implements CommandLineRunner {
	@Value("${app.twilo.id}")
	private String twilioId;
	@Value("${app.twilo.authid}")
	private String authid;
	@Value("${app.twilo.tnumber}")
	private String tnumber;
	
	private static final Logger logger= LoggerFactory.getLogger(TwilioControllerComponent.class);
	private static final String T_ID="ACfa78a6e5b3e391a3431d8272c237b614";
	private static final String T_AUTH_ID="18e703301747780f3cd7f7d7277faceb";
	public static final String T_NUMBER="+16502065490";
	//+15046845213
	
//	static {
//		Twilio.init(twilioId, authid);
//	}
//	
	@Autowired
	private TwilioSMSService smsService;
	
	@Override
	public void run(String... args) throws Exception {
		Twilio.init(twilioId, authid);
			logger.info("Twilliocontroller started:init::->OK");
			smsService.runSMS(T_NUMBER, "+34601371747");
			logger.info("App:Controller::(service)finished sending...");
			
		
	}
	

}
