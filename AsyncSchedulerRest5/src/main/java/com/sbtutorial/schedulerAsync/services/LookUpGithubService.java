package com.sbtutorial.schedulerAsync.services;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sbtutorial.schedulerAsync.models.User;


@Service
public class LookUpGithubService {
	  private static final Logger logger = LoggerFactory.getLogger(LookUpGithubService.class);
	  private static final String GITHUB_URL = "https://api.github.com/users/%s";
	  
	  private final RestTemplate restTemplate;//REST CLIENT injection
	  public LookUpGithubService(RestTemplateBuilder restTemplateBuilder) {
	    this.restTemplate = restTemplateBuilder.build();
	  }
	  
	  @Async
	  public CompletableFuture<User> findUser(String user) throws InterruptedException {
	    logger.info("Looking up " + user);
	    String url = String.format(GITHUB_URL, user);
	    
	    User results = restTemplate.getForObject(url, User.class);
	    
	    logger.info("finding " + user);
	    try {
			logger.info(""+ CompletableFuture.completedFuture(results).get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

	    return CompletableFuture.completedFuture(results);
	  }

}
