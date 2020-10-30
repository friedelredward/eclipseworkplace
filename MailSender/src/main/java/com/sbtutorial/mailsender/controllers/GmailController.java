package com.sbtutorial.mailsender.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
public class GmailController implements CommandLineRunner{
	
	@Autowired
	private JavaMailSender gmailSender;
	
	@Override
	public void run(String... args) throws Exception {
		System.out.println("Sending mail....");
		sendEmail();
		
		System.out.println("Mail sent!!!");
	}
	
	private void sendEmail() {
		SimpleMailMessage msg= new SimpleMailMessage();
		
		msg.setSubject("Simplejava mail sender");
		msg.setTo("redwardweb18@gmail.com");
		msg.setText("Test4eo desde spring");
		
		gmailSender.send(msg);
	}

}
