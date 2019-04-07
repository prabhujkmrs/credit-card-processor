package com.prabhuj;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.prabhuj.*")
public class CreditCardApplication {

	public static void main(String[] args) {
		//SpringApplication.run(CreditCardApplication.class, args);
		SpringApplicationBuilder builder = new SpringApplicationBuilder(CreditCardApplication.class);
		builder.headless(false).run(args);

	}

}
