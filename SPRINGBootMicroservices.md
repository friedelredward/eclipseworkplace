# Sping boot microservices

[![Generic badge](https://img.shields.io/badge/<Spring>-<Docs>-00ff7f.svg)](https://www.baeldung.com/inversion-control-and-dependency-injection-in-spring)
- runs on maven// emmbed Tomcat
- spring boot takes care of absolutely everithing vs spring u can configure for urself. boot wil automaticly serve prepared default in case of missing dev customs implementations.
- **IoC** inversion of control == mechanism to help developer with  DepInjection and concentrate on Design patterns. thanks to ApplicationContext. just declare dem and IoC takes care o instantiating and passing the object. pej:
    - endpoints / databse Crud / JavaMessaginApi / monitoring 

- Bean=== spring object
- tymeleaf == SS template engine; transforms data before presenting to user; `Spring boot starter tymeleaf`
- `spring-boot-starter-security`
-----------------

## 1st Steps
- maven project `maven-archetype-quickstart` Artifact id ,groupId, usually packer namespace reversed
    - Update pom to accept boot defaults(configs and such)

```xml
    +++	<packaging>jar</packaging>
    <parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.3.1.RELEASE</version>
		<relativePath />
	</parent>
    .....under Dependencys
    	<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter</artifactId>
		</dependency>
```
    - Different boot starters for app type(web, rest, etc)
- *pom.xml* === depInjection

<details>
    <summary>Annotations</summary>
    
```java
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan
//	SAME AS
@SpringBootApplication
```
</details>

> RememebER! *rClick> maven>update Proj* (after DI/pom update)
> At this point ur *RUNNING*
- *Cltrl+Sift+O === autoimports class*

---------------------

## Running embbed TOMCAT

- Enabling MCV 
```xml
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<!--redudndante el tomcat viene incluido pero para saber como usar del padre dependency-->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-tomcat</artifactId>
		</dependency>
```
<details>
    <summary>HelloController</summary>
    
```java
package com.springBootFirst.quickstart.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(){
		return "Hellow from home";
	}

}
```
</details>


## Alternatives: Jetty// external *.war* _(pej. jBoss)_

- Another great ServletContainer included in `-boot-web` :: *Jetty*// *Undertow*
- exclude dependency from package pej `starter-web`

<details>
    <summary>Jetty</summary>
    
```xml
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
			<exclusions>
				<exclusion>
					<groupId>org.springframework.boot</groupId>
					<artifactId>spring-boot-starter-tomcat</artifactId>
				</exclusion>
			</exclusions>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-jetty</artifactId>
		</dependency>
```

> Thats it!

</details>

<details>
    <summary>External: Have to specify to Tomcat + make as a war file+ runAs Maven build+deploy it(any server)</summary>
    
```xml
tomcatDependency
	<scope>provided</scope>
main
	<packaging>war</packaging>

```
> java mainAppClass + its implementation
```java
extends SpringBootServletInitializer 
        @Override
        protected SpringApplicationBuilder configure(SpringApplication application) {
        	return application.sources(App.class);
        }
```

</details>

-----------------

## Spring Initializer (alternative to 1steps to bootstrap project)
- https://start.spring.io/

## i18n
- add dependency `starter-web-tymeleaf`
- add Intern..Service `implements WebMcvConfigurer` + resources/messages.properties + `re/meesager_lang.properties`

## BestInClass boot tools connect internally to initializer + initial dependencys

## adding cmd line args for spring running config
> rCick> run configs>select app>arguments(tab) :pej: `--server.port=7070`
//
> or in `res../application.properties` (has autocomplete) :pej: `server.port=8888`

## Autowiring works!!!
> in bean class `@Component`; at use `@Autowired + instantiation;`
- for properties injection(params)
> dependency `spring-boot-configuration-processor` + aplication.properties + click createMetadata

# Cache implementation

- new project>spring>`RestfulApi` `with starter-web` and `configuration-processor`
<details>
    <summary>Injection of Service for CRUD controller operations(no ORM + no DBase)
	</summary>

> autowired Service(as repository)

```java
package com.springBootRest.RestfulApi.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.springBootRest.RestfulApi.models.Product;

@Service
public class ProductService {
	private List<Product> products=Arrays.asList(
			new Product("1", "ordenador1"),
			new Product("2", "ordenador2"),
			new Product("3", "ordenador3"),
			new Product("4", "ordenador4")
			);
	
			public List<Product> listProducts() {
				return this.products;
			}
			
			public Product getProduct(String id) {
				return this.products.stream()
						.filter(p-> p.getId().equals(id))
						.findFirst()
						.get();
			}
			
}

```

> refactored Controller
```java
@RestController
public class ProductController {
	@Autowired
	private ProductService pService;
	
	@GetMapping("products")
	public List<Product> listProducts(){
		return pService.listProducts();
	}
	
	@GetMapping("/products/{id}")
	public Product getById(@PathVariable("id") String pId){
		return pService.getProduct(pId);
	}
}
```
### for POST and PUT, DELTE USE `@RequestBody`

</details>

<details>
    <summary>*CRUD with Hypernate and mysql*, ojo se implementa excepcion(el controller no cambia at all)
	</summary>

- Could have used `Spring JPA Data starter` + hybernate +mysql conector!!!!!

>very important to configure `aplication.properties` so jpa and hibernate work!!!!
```
spring.datasource.url=jdbc:mysql://localhost:3306/restful_spring
spring.datasource.username=root
spring.datasource.password=admin
spring.jpa.hibernate.ddl-auto=create
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```
>refactored with hybernate and jpa data. !!!!OJO los IMPORTS!! `javax.persistence`
```java
package com.springBootRest.RestfulApi.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="prducts")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	public Product(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	public Product() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
```
> ProductService; _extra layer of abstraction_
```java
package com.springBootRest.RestfulApi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springBootRest.RestfulApi.models.Product;
import com.springBootRest.RestfulApi.repos.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepo;

	public List<Product> listProducts() {
		List<Product> products = new ArrayList<>();
		this.productRepo.findAll().forEach(products::add);
		return products;
	}

	public Optional<Product> getProduct(Long id) {
		return productRepo.findById(id);
	}

	public void addProduct(Product p){
		productRepo.save(p);
	}

	public void updateProduct(Long id, Product p){
		if (productRepo.findById(id).get() != null) {
			productRepo.save(p);
		}
	}

	public void deleteProduct(Long id){
		productRepo.deleteById(id);
	}
}
```
> productRepository
```java
package com.springBootRest.RestfulApi.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springBootRest.RestfulApi.models.Product;

@Repository
public interface ProductRepository  extends CrudRepository<Product, Long>{

}
```
>productNotFoundResponse // ProductNotFoudException
```java
package com.springBootRest.RestfulApi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ProductNotFoundResponse {

	@ResponseBody
	@ExceptionHandler(ProductNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String productNotFoundHandler(ProductNotFoundException exception) {
		return exception.getMessage();
	}
}
//------------------------------------------
package com.springBootRest.RestfulApi.exceptions;

public class ProductNotFoundException extends RuntimeException{
	private static final long serialVersionUI= 1L;
	
	public ProductNotFoundException(Long id) {
		super("The Product with id:"+id+" cannot be found");
	}

}
```

</details>

<details>
	<summary>TO RUN COMMAND AT APP START-UP(:pej: inseert dummy-data into Dbase)</summary>

```java
public class RestfulApiApplication implements CommandLineRunner{
	@Autowired
	private ProductRepository productRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(RestfulApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<Product> products=Arrays.asList(
				new Product( "ordenador1"),
				new Product( "ordenador2"),
				new Product( "ordenador3"),
				new Product( "ordenador4")
				);
		productRepo.saveAll(products);
	}
}
```
> Any @Entity can be wrapped into `Model` so u can renderit with `Thymeleaf`
</details>


<details>
	<summary>Implementing cache `@Cacheable`</summary>
- dependency : `spring-boot-starter-cache`

> enable it (annotations AplicationClass lvl)
```java
@SpringBootApplication
@EnableCaching
public class RestfulApiApplication implements CommandLineRunner{
....
}
```
> Have to use it at a layer common to all needs :pej: `ProductService`
- from `import org.springframework.cache.annotation.Cacheable;`
```java
	@Cacheable("products")
	public List<Product> listProducts() {
		List<Product> products = new ArrayList<>();
		this.productRepo.findAll().forEach(products::add);
		return products;
	}
```
- EXPLnation: code with cached data will not even run, (pej repository), getting all resuts from cache , MUCH FASTER.
- When we modify a value that's cached, we need to update it. so we purge the cache in the method that updates value or value from list with `@cacheEvict(value="products", allEntries=true)`
```java
	@CacheEvict(value="products", allEntries=true)
	public void addProduct(Product p){
		productRepo.save(p);
	}
```
> multiple Evicts// operation affects more han 1 chced value(:pej: list and single element)
```java
	@CacheEvict(value="products", allEntries=true)
	public void addProduct(Product p){
		productRepo.save(p);
	}
//...
	@Caching(evict= {
			@CacheEvict(value="product", key="#p0"),
			@CacheEvict(value="products", allEntries=true)
	})
	public void updateProduct(Long id, Product p){
		if (productRepo.findById(id).get() != null) {
			productRepo.save(p);
		}
	}
```
</details>

> para no recrear la DBase cada vez e ir sumando valores en application.properties: `spring.jpa.hibernate.ddl-auto=update`

# Async Methods, Schedulers, forms
[![Generic badge](https://img.shields.io/badge/GuideAt-<Spring>-00ff7f.svg)](https://spring.io/guides/gs/async-method/)
- repaso clave: `@Service, @Component, @Repository` enable `@Autowired` + repo:` formsValAsyncscheduler_3@springMicroservices repo`
> `@Async` makes a  CompletableFuture<User> *method* to run on  _separate Thread_(parallel)
- `@EnableAsync` requires  a `taskExecutor()` + anotated with `@Bean` so can be injected
> We retrieve a JSON and need a Bean with spesific atributes so we use `@JsonIgnoreProperties(ignoreUnknown=true)` // uses `jaxon` library by default
- `@Component `tells Spring to manage it and invoke it. if it's  a `CommandlineRunner` will invoke the run execution
> Await multiple threads runs(marked with async) finishing (depending on poolsize== parallelthreads count); can be sincronized again with:
```java
CompletableFuture.allOf(user1,user2,user3,user4,user5,user6)
	    					.join();
```
Check tambien project`stsspringboot_2async` porque si no encuentra o fallta el RESTclient peta al interntar loguear

## scheduler
based on `spring-boot-starter`... my proj:*scheduler4*
[![Generic badge](https://img.shields.io/badge/GuideAt-<Spring>-00ff7f.svg)](https://spring.io/guides/gs/scheduling-tasks/)
- `@EnableScheduling`:: will bring  `taskExecutor` for the *background*.
> this time we DO NOT `.close()` app
```java
	@Scheduled(fixedRate = 3000)//with fixed rate each process is independent and
	//WILL NOT start unless the other finishes
	/**with aplication.properties::: scheduler.rate= 4000*/
		@Scheduled(fixedRateString = "${scheduler.rate}", initialDelay = 5000)
```

## Scheduling + Async (:project: AsyncSchedulerRest5  done with `starter-web`)
Running on *DIFFERENT* background threads(__parallel__)
## Forms(starter-web+thymeleaf)
- By **default** `@Controller` any mmapping(route) returns __logical_VIEWS__

# JavaMailSender
- `starter-web`+`thymeleaf`+ `spring-boot-starter-mail`
- gmail :pej: `+Enable IMAP+` + `lessecureapps`> ON
> `@Configuration` specifies it's not a controller but a config file for the app. if we tag it with `CommandLineRunner` spring will autoexec it!
- MIME msg for using attachements
## interceptors
- web mvc; any web REQ is intercepted by dispatcher servlet -> handler mappings to match. _Middleware_ :: parse requests before sending them to handler method(route)
> `implements HandlerInterceptor`  + `@Component` in controller + 
```java
package.... .config
@Component 
public class WebAppConfig implements WebMvcConfigurer{
	@Autowired
	private AnyInterceptor interceptorInjected;
	
	@Override
	public void addInterceptors(InterceptorRegistry reg){
		reg.addInterceptor(interceptorInjected);
	}
}
```
- `preHandler`== before it reaches endpoint u can modify request... etc `return true`
- `postHandler`== method executed but view NOT rendered. :pej adding something to the model to display:
- `afterCompletion` after ALL has been executed








