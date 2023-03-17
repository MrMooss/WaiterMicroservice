package com.restaurant.waiter;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@OpenAPIDefinition(
		servers = {
				@Server(url = "http://localhost:8080/order", description = "local") },

		info = @Info(
				title = "Waiter API",
				version = "",
				description = "description = \"Waiter API for Graphical User Interface ."))

@Configuration
@EnableWebMvc
@EnableJpaRepositories("com.restaurant.waiter.Service")
@EntityScan("com.restaurant.waiter.model")
@SpringBootApplication(scanBasePackages = "com.restaurant.waiter")
public class WaiterApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(WaiterApiApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**");
			}
		};
	}

}
