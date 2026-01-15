package com.nafiu.moviereservationservice.auth.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Nafiu Lawal",
                        email = "nafiudanlawal@gmail.com"
                ),
                description = "OpenApi doc for Movie reservation service",
                version = "1.0.0",
                license = @License(
                        name = "MIT License",
                        url = "https://www.opensource.org/licenses/mit-license.php"
                )
        ),
        servers = {
                @Server(url = "http://localhost:8080", description = "Local environment")
        },
        security = {
                @SecurityRequirement(
                        name = "Bearer Authorization"
                )
        }
)
@SecurityScheme(
        name = "Authorization",
        description = "JWT auth description",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {
}
