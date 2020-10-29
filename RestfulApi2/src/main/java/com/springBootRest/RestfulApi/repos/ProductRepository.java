package com.springBootRest.RestfulApi.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springBootRest.RestfulApi.models.Product;

@Repository
public interface ProductRepository  extends CrudRepository<Product, Long>{

}
