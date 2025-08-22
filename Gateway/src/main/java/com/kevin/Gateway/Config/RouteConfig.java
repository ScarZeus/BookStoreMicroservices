package com.kevin.Gateway.Config;

import com.kevin.Gateway.handler.UserResponseHandler;
import org. springframework. web. servlet. function. ServerResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.servlet.function.RequestPredicates.accept;
import static org.springframework.web.servlet.function.RouterFunctions.route;

@Configuration
public class RouteConfig {

    @Bean
    public RouterFunction<ServerResponse> userRouter(){
        UserResponseHandler handler = new UserResponseHandler();
        RouterFunction<ServerResponse> route =
                route()
                        .GET("/person/{id}", accept(APPLICATION_JSON), handler::getAllBooks)
                        .GET("/person", accept(APPLICATION_JSON), handler::getBooks)
                        .POST("/person", handler::addBook)
                        .build();
    }


}
