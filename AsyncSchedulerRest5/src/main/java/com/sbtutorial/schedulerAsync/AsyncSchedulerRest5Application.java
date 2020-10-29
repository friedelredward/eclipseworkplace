package com.sbtutorial.schedulerAsync;

import java.util.concurrent.Executor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SpringBootApplication
@EnableAsync
@EnableScheduling
public class AsyncSchedulerRest5Application {

	public static void main(String[] args) {
		SpringApplication.run(AsyncSchedulerRest5Application.class, args);
	}
	
	@Bean
	public Executor taskExecutor() {
		ThreadPoolTaskExecutor executor= new ThreadPoolTaskExecutor();
		/*note the 1 thread*/
		executor.setCorePoolSize(1);
		executor.setMaxPoolSize(1);
		executor.setQueueCapacity(500);
		executor.setThreadNamePrefix("GithubLookup-");
		executor.initialize();
		
		return executor;
		
	}

}
