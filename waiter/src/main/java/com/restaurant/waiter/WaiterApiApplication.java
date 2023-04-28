package com.restaurant.waiter;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.OAuthFlows;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
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

@SecurityScheme(
		type = SecuritySchemeType.OAUTH2,
		name = "oauth2",
		description = "KeyCloak WatierAPI",
		flows = @OAuthFlows(
				implicit = @OAuthFlow(authorizationUrl = "http://restaurant:6080/auth/realms/WaiterAPI/protocol/openid-connect/auth"
						+ "?client_id=account"
						+ "&redirect_uri=http://restaurant:8080/orderTable/swagger-ui/oauth2-redirect.html"
						+ "&response_type=code"
						+ "&scope=openid")
		)
)

@SecurityScheme(
		type = SecuritySchemeType.APIKEY,
		name = "apikey",
		paramName = "Authorization",
		description = "KeyCloak WaiterAPI",
		in = SecuritySchemeIn.HEADER)


@SecurityScheme(
		type = SecuritySchemeType.OPENIDCONNECT,
		name = "openid",
		description = "KeyCloak WatierAPI",
		openIdConnectUrl = "http://restaurant:6080/auth/realms/WaiterAPI/.well-known/openid-configuration"
)

@OpenAPIDefinition(
		servers = {
				@Server(url = "http://restaurant:8080/orderTable", description = "local") },

		info = @Info(
				title = "Waiter API",
				version = "v0.8",
				description = "Waiter API for Graphical User Interface."))

@Configuration
@EnableJpaRepositories("com.restaurant.waiter.Service")
@EntityScan("com.restaurant.waiter.model")
@SpringBootApplication(scanBasePackages = "com.restaurant.waiter")
public class WaiterApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(WaiterApiApplication.class, args);
	}

}
