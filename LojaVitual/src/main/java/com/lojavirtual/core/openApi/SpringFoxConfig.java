package com.lojavirtual.core.openApi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SpringFoxConfig {
	
	
	@Bean
	public OpenAPI openAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("LojaVitual - Rest API")
						.description("API de loja virtual")
						
						.version("1.0")
						.termsOfService("Termo de uso:Open Source")
						.license(new License()
								.name("Apache 2.0")
								.url("www.reneantunesdev.com.br")))
						.externalDocs(new ExternalDocumentation()
								.description("Rene Antunes")
								.url("www.reneantunesdev.com.br"));
						
	}
	
}
