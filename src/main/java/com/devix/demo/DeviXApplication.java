package com.devix.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan("com.devix.demo.model")
@ComponentScan(basePackages = "com.devix.demo")
public class DeviXApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeviXApplication.class, args);
	} 
}
