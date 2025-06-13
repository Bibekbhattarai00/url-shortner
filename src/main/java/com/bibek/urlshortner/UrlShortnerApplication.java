package com.bibek.urlshortner;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaAuditing
@OpenAPIDefinition(
        info = @Info(title = "URL Shortner API", version = "V-1.0", description = "API Documentation"),
        servers = {@Server(url = "https://url-shortner-r0pv.onrender.com/url-shortner",
                description = "Deployed Server URL"),
                @Server(url = "http://localhost:9091/url-shortner",
                        description = "Local Server URL")})
public class UrlShortnerApplication {

    public static void main(String[] args) {
        SpringApplication.run(UrlShortnerApplication.class, args);
    }

}

