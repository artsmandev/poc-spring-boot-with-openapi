package dev.artsman.poc.oas;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.stereotype.Component;

@Component
@OpenAPIDefinition(
	info = @Info(
		title = "POC Spring Boot with OpenAPI",
		summary = "Just a Poc Spring Boot with OpenAPI",
		description = "POC to validate how to implement Spring Boot 3 and OpenAPI 3",
		termsOfService = "https://openlearninglibrary.mit.edu/tos",
		contact = @Contact(
			name = "Bruno Andrade",
			url = "https://artsman.dev",
			email = "bruno@artsman.dev"
		),
		license = @License(
			name = "MIT License",
			identifier = "MIT",
			url = "https://www.mit.edu/~amini/LICENSE.md"
		),
		version = "0.1.1-SNAPSHOT"
	),
	servers = @Server(
		url = "http://localhost:8080",
		description = "Development"
	)
)
public final class OpenApiDefinition {}
