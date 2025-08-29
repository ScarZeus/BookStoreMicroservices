package com.kevin.gateway.config;

import com.kevin.gateway.filterer.AuthFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfig {

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("auth-login", r -> r.path("/login")
                        .uri("http://localhost:5000")) // auth-service
                .route("auth-signup", r -> r.path("/signup")
                        .uri("http://localhost:5000"))

                .route("book-getAll", r -> r.path("/getAllBooks")
                        .filters(f -> f.filter(new AuthFilter()))
                        .uri("http://localhost:5010"))
                .route("book-add", r -> r.path("/addBook")
                        .filters(f -> f.filter(new AuthFilter()))
                        .uri("http://localhost:5010"))
                .route("book-delete", r -> r.path("/delete")
                        .filters(f -> f.filter(new AuthFilter()))
                        .uri("http://localhost:5010"))
                .route("book-update", r -> r.path("/update")
                        .filters(f -> f.filter(new AuthFilter()))
                        .uri("http://localhost:5010"))
                .route("book-getByName", r -> r.path("/getBooks")
                        .filters(f -> f.filter(new AuthFilter()))
                        .uri("http://localhost:5010"))
                .route("billing-create", r -> r.path("/createBill")
                        .filters(f -> f.filter(new AuthFilter()))
                        .uri("http://localhost:5015"))
                .route("billing-user", r -> r.path("/user/**")
                        .filters(f -> f.filter(new AuthFilter()))
                        .uri("http://localhost:5015"))
                .route("billing-delete", r -> r.path("/deleteBill/**")
                        .filters(f -> f.filter(new AuthFilter()))
                        .uri("http://localhost:5015"))
                .route("user-getByEmail", r -> r.path("/getUserByEmail")
                        .filters(f -> f.filter(new AuthFilter()))
                        .uri("http://localhost:5020"))
                .route("user-add", r -> r.path("/addNewUser")
                        .filters(f -> f.filter(new AuthFilter()))
                        .uri("http://localhost:5020"))
                .build();
    }
}
