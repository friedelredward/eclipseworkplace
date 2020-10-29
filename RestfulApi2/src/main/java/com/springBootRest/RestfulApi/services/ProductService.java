package com.springBootRest.RestfulApi.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import com.springBootRest.RestfulApi.models.Product;
import com.springBootRest.RestfulApi.repos.ProductRepository;

@Service
public class ProductService {
	private List<Product> products=Arrays.asList(
			new Product( "ordenador1"),
			new Product( "ordenador2"),
			new Product( "ordenador3"),
			new Product( "ordenador4")
			);
	
	@Autowired
	private ProductRepository productRepo;
	
	@Cacheable("products")
	public List<Product> listProducts() {
		List<Product> products = new ArrayList<>();
		this.productRepo.findAll().forEach(products::add);
		return products;
	}
	
	@Cacheable(value="product", key="#p0")
	public Optional<Product> getProduct(Long id) {
		return productRepo.findById(id);
	}

	@CacheEvict(value="products", allEntries=true)
	public void addProduct(Product p){
		productRepo.save(p);
	}

	@Caching(evict= {
			@CacheEvict(value="product", key="#p0"),
			@CacheEvict(value="products", allEntries=true)
	})
	public void updateProduct(Long id, Product p){
		if (productRepo.findById(id).get() != null) {
			productRepo.save(p);
		}
	}

	public void deleteProduct(Long id){
		productRepo.deleteById(id);
	}

}
