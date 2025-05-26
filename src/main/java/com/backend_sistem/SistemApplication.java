package com.backend_sistem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SistemApplication extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(SistemApplication.class, args);
	}
	  private static Class<SistemApplication> sisaceApiApplication = SistemApplication.class;

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	 @Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder){  return builder.sources(sisaceApiApplication);    }

}

