package com.springBootRest.RestfulApi.exceptions;

public class ProductNotFoundException extends RuntimeException{
	private static final long serialVersionUI= 1L;
	
	public ProductNotFoundException(Long id) {
		super("The Product with id:"+id+" cannot be found");
	}

}
