package it.awesome.pizza;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@EnableAutoConfiguration
@ComponentScan({ "it.awesome.*"})
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class AwesomePizzaApplication extends SpringBootServletInitializer {

	@SuppressWarnings("unused")
	private static final org.slf4j.Logger log = LoggerFactory.getLogger(AwesomePizzaApplication.class);


	@Value(value = "${spring.jackson.time-zone}")
	private String defaultTimeZone;
	
	
	public static void main(String[] args) {
		SpringApplication.run(AwesomePizzaApplication.class, args);
	}


	@PostConstruct
	public void started() {
		TimeZone.setDefault(TimeZone.getTimeZone(defaultTimeZone));
	}

	
}
