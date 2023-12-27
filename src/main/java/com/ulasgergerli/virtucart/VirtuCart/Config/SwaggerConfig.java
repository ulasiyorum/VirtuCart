package com.ulasgergerli.virtucart.VirtuCart.Config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Virtu Cart API")
                        .version("1.0")
                        .description("Virtu Cart API Documentation")
                        .termsOfService("http://swagger.io/terms/")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org")
                        )
                        .contact(new Contact()
                                .email("20200808049@ogr.akdeniz.edu.tr")
                                .name("Ulas Gergerli")
                                .url("https://verdantdev.co")
                        )
                );
    }
}