package com.springBootRest.RestfulApi.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springBootRest.RestfulApi.models.Product;
import com.springBootRest.RestfulApi.services.ProductService;

@RestController
public class ProductController {
	@Autowired
	private ProductService pService;
	
	@GetMapping("/products")
	public List<Product> listProducts(){
		return pService.listProducts();
	}
	
	@GetMapping("/products/{id}")
	public Product getById(@PathVariable("id") Long pId){
		return pService.getProduct(pId).get();
	}
	
	@PostMapping("/products")
	public void addProduct(@RequestBody Product newProduct) {
		pService.addProduct(newProduct);
	}
	
}
