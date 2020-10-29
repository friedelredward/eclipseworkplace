package com.sbtutorial.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MySimpleScheduler {
	private static final SimpleDateFormat DATE_FORMAT= new SimpleDateFormat("HH:mm:ss");

	@Scheduled(fixedRateString = "${scheduler.rate}", initialDelay = 5000)
	private void scheduledLookUp() {
		System.out.println("The time is:"+ DATE_FORMAT.format(new Date()));
		
		//custom Delay
		try {
			Thread.sleep(5000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}