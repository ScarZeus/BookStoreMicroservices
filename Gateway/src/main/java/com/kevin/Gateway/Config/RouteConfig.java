package com.kevin.Gateway.Config;

import com.kevin.Gateway.handler.BookResponseHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;


@Configuration
@RequiredArgsConstructor
public class RouteConfig {

    private final BookResponseHandler bookResponseHandler;

    @Bean
    public RouterFunction<ServerResponse> bookRouter() {
        return route()
                // GET all books
                .GET("/books/getAllBooks", accept(MediaType.APPLICATION_JSON), bookResponseHandler::getAllBooks)

                // GET books by name/ibsno
                .GET("/books/getBooks", accept(MediaType.APPLICATION_JSON), bookResponseHandler::getBooks)

                // POST add book
                .POST("/books/addBook", accept(MediaType.MULTIPART_FORM_DATA), bookResponseHandler::addBook)

                // DELETE book (use query param for ID)
                .DELETE("/books/delete", bookResponseHandler::deleteBook)

                // POST update book
                .POST("/books/update", accept(MediaType.APPLICATION_JSON), bookResponseHandler::updateBook)
                .build();
    }


}
