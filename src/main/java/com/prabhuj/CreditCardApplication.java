package com.prabhuj;

import com.prabhuj.domain.Card;
import org.hsqldb.util.DatabaseManagerSwing;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;

import javax.annotation.PostConstruct;

@SpringBootApplication
@ComponentScan(basePackages = "com.prabhuj.*")
public class CreditCardApplication {

	public static void main(String[] args) {
		//SpringApplication.run(CreditCardApplication.class, args);
		SpringApplicationBuilder builder = new SpringApplicationBuilder(CreditCardApplication.class);
		builder.headless(false).run(args);

	}

	@PostConstruct
	public void getDbManager(){
		DatabaseManagerSwing.main(
				new String[] { "--url", "jdbc:hsqldb:mem:testdb", "--user", "sa", "--password", ""});
	}

	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasenames("messages");
		return messageSource;
	}

}
