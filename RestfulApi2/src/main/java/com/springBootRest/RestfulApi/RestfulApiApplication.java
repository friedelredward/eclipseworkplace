package com.springBootRest.RestfulApi;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import com.springBootRest.RestfulApi.models.Product;
import com.springBootRest.RestfulApi.repos.ProductRepository;

@SpringBootApplication
@EnableCaching
public class RestfulApiApplication implements CommandLineRunner{
	
	@Autowired
	private ProductRepository productRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(RestfulApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<Product> products=Arrays.asList(
				new Product( "ordenador11"),
				new Product( "ordenador22"),
				new Product( "ordenador32"),
				new Product( "ordenador42")
				);
		productRepo.saveAll(products);
	}
	

}
