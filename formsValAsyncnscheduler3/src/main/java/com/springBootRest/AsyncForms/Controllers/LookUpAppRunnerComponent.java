package com.springBootRest.AsyncForms.Controllers;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.springBootRest.AsyncForms.Services.LookUpGitService;
import com.springBootRest.AsyncForms.models.User;

@Component
public class LookUpAppRunnerComponent implements CommandLineRunner{
	private static final Logger logger = LoggerFactory.getLogger(LookUpAppRunnerComponent.class);
/**
 * 
	private final LookUpGitService gitService;
	public LookUpAppRunnerComponent(LookUpGitService gitService) {
		this.gitService = gitService;
	}
	REPLACED BY*/
	@Autowired
	private LookUpGitService gitService;

	@Override
	public void run(String... args) throws Exception {
		// Start the clock
	    long start = System.currentTimeMillis();
	    
	/** Kick of multiple, asynchronous(parallel) lookups which will  use poll of threads at need when available
	 */
		CompletableFuture<User> info1 = gitService.findUser("Pytorch");
		CompletableFuture<User> info2 = gitService.findUser("Tensorflow");
		CompletableFuture<User> info3 = gitService.findUser("Scikit-learn");
		CompletableFuture<User> info4 = gitService.findUser("spring-boot");
		CompletableFuture<User> info5 = gitService.findUser("spring-mvc");
		CompletableFuture<User> info6 = gitService.findUser("spring-security");
		
		CompletableFuture.allOf(info1,info2,info3,info4,info5,info6).join();
		
		logger.info("Elapsed time: " + (System.currentTimeMillis() - start));
		logger.info("--> " +info1.get());
		logger.info("--> " +info2.get());
		logger.info("--> " +info3.get());
		logger.info("--> " +info4.get());
		logger.info("--> " +info5.get());
		logger.info("--> " +info6.get());
	}

}
