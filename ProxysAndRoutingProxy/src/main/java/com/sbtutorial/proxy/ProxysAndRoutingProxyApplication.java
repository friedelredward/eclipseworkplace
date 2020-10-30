package com.sbtutorial.proxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.sbtutorial.proxy.filters.ErrorFilter;
import com.sbtutorial.proxy.filters.PostFilter;
import com.sbtutorial.proxy.filters.PreFilter;
import com.sbtutorial.proxy.filters.RoutingFilter;

@SpringBootApplication
@EnableZuulProxy
public class ProxysAndRoutingProxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProxysAndRoutingProxyApplication.class, args);
	}

	@Bean
	public RoutingFilter routingFilter() {
		return new RoutingFilter();
	}

	@Bean
	public PreFilter preFilter() {
		return new PreFilter();
	}

	@Bean
	public PostFilter postFilter() {
		return new PostFilter();
	}

	@Bean
	public ErrorFilter errorFilter() {
		return new ErrorFilter();
	}
}
