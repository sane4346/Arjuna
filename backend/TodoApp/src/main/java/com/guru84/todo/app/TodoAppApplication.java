package com.guru84.todo.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.guru84.todo.app.security.AppProperties;

@SpringBootApplication
public class TodoAppApplication extends SpringBootServletInitializer {
	//overriden method from Spring boot Servlet Initializer
	@Override
	 protected SpringApplicationBuilder configure(SpringApplicationBuilder applicationBuilder) {
		return applicationBuilder.sources(TodoAppApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(TodoAppApplication.class, args);
	}
	
	@Bean
	BCryptPasswordEncoder bCrypPassowrdEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	SpringApplicationContext springApplicationContext()
	{
		return new SpringApplicationContext();
	}
	
	@Bean(name = "AppProperties")
	AppProperties getAppProperties()
	{
		return new AppProperties();
	}

}
