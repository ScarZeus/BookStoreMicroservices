package com.kevin.Gateway.Config;


import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
@RequiredArgsConstructor
public class RouteConfig {

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("book-service", r -> r.path("/api/v1/books/**")
                        .uri("http://localhost:5010"))
                .route("user-service", r -> r.path("/api/v1/users/**")
                        .uri("lb://USER-SERVICE"))

                .build();
    }
}

