package com.company.recap3.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerConfig {

	@Bean
	OpenAPI openApiInformation() {
		Server localServer = new Server().url("http://localhost:8000").description("Localhost Server URL");
		Contact contact = new Contact().email("supratimmajumder531@gmail.com").name("Supratim Majumder");
		Info info = new Info().contact(contact).description("Spring Boot 3 + Open API 3 + JPA CRUD")
				.summary("This is my 3rd recap project after a long time").title("Spring Boot 3 + Open API 3")
				.version("v1.0.0")
				.license(new License().name("github@supratim531").url("https://github.com/supratim531"));
		return new OpenAPI().info(info).addServersItem(localServer);
	}

}
