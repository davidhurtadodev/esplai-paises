package io.nuwe.Hackaton_FundacionEsplai.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Environmental APP", version = "1.0.0", description = "Documentation Environment API"), security = @SecurityRequirement(name = "bearerToken"))
@SecurityScheme(name = "bearerToken", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT")
public class OpenApiConfig {

    public static final String[] PUBLIC_PATHS = {"/swagger-ui/index.html", "/swagger-ui.html","/swagger-ui/**", "/v3/api-docs", "/v3/api-docs/**", "/webjars/**", "/swagger-resources/**", "/api/register", "/api/login"};

}
