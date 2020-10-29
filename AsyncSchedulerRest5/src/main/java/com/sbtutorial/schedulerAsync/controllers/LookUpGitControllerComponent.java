package com.sbtutorial.schedulerAsync.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.sbtutorial.schedulerAsync.models.User;
import com.sbtutorial.schedulerAsync.services.LookUpGithubService;


@Component
public class LookUpGitControllerComponent {
	  private static final Logger logger = LoggerFactory.getLogger(LookUpGitControllerComponent.class);
	  private static int userINDEX=0;
	  
	  @Autowired
	  private LookUpGithubService lookUpGithubService;
	  
	  private static final List<String> usersList= new ArrayList<String>();
	  //static initialization block !!!!
	  static {
		  usersList.add("Pytorch");
		  usersList.add("Tensorflow");
		  usersList.add("Scikit-learn");
		  usersList.add("spring-boot");
		  usersList.add("spring-mvc");
		  usersList.add("spring-security");  
	  }

		@Scheduled(fixedRateString = "${scheduler.rate}", initialDelay = 1000)
		private void scheduledLookUpTasks()throws Exception {
			CompletableFuture<User> info= lookUpGithubService.findUser(usersList.get(userINDEX));
			
			
			userINDEX= (userINDEX+1)% usersList.size();/*when reachez end restarts at 1*/
			//custom Delay
			try {
				Thread.sleep(5000L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			logger.info("from Component(Controller)"+info.get());
		}
}
