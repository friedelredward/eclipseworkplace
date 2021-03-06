package com.springBootRest.AsyncForms.Services;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.springBootRest.AsyncForms.models.User;

@Service
public class LookUpGitService {
	  private static final Logger logger = LoggerFactory.getLogger(LookUpGitService.class);
	  private static final String GITHUB_URL = "https://api.github.com/users/%s";
	  
	  private final RestTemplate restTemplate;//REST CLIENT

	  public LookUpGitService(RestTemplateBuilder restTemplateBuilder) {
	    this.restTemplate = restTemplateBuilder.build();
	  }

	  @Async
	  public CompletableFuture<User> findUser(String user) throws InterruptedException {
	    logger.info("Looking up " + user);
	    String url = String.format(GITHUB_URL, user);
	    
	    User results = restTemplate.getForObject(url, User.class);
//	    logger.info("finding " + user);
//	    logger.info( user);
	    // Artificial delay of 1s for demonstration purposes
	    Thread.sleep(4000L);
	    return CompletableFuture.completedFuture(results);
	  }

}
