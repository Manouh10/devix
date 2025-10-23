package com.devix.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

import com.devix.demo.model.Currency;
import com.devix.demo.utils.MockExchangeRate;

@SpringBootApplication
@EntityScan("com.devix.demo.model")
@ComponentScan(basePackages = "com.devix.demo")
public class DeviXApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeviXApplication.class, args);
		/*MockExchangeRate mock= new MockExchangeRate();
		Currency mga=new Currency();mga.setName("MGA");
		Currency eur=new Currency();eur.setName("EUR"); 
		  System.out.println("Hello, Spring Boot is running!");
		  System.out.println( mock.getRate(eur, mga));*/ 
	} 
}
