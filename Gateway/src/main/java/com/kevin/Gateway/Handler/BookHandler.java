//package com.kevin.Gateway.Handler;
//
//import org.springframework.stereotype.Component;
//
//@Component
//public class BookHandler {
//
//        private final WebClient webClient;
//
//        public BookResponseHandler(WebClient.Builder webClientBuilder) {
//            this.webClient = webClientBuilder.baseUrl("http://localhost:5010").build(); // BookService URL
//        }
//
//        public Mono<ServerResponse> getAllBooks(ServerRequest request) {
//            System.out.println("Check 1");
//            return webClient.get()
//                    .uri("/api/v1/books/getAllBooks")
//                    .retrieve()
//                    .bodyToMono(String.class)
//                    .flatMap(body -> ServerResponse.ok().bodyValue(body))
//                    .onErrorResume(e -> ServerResponse.status(500).bodyValue(e.getMessage()));
//        }
//
//        public Mono<ServerResponse> addBook(ServerRequest request) {
//            return request.bodyToMono(String.class)
//                    .flatMap(body ->
//                            webClient.post()
//                                    .uri("/books/addBook")
//                                    .bodyValue(body)
//                                    .retrieve()
//                                    .bodyToMono(String.class)
//                    )
//                    .flatMap(res -> ServerResponse.ok().bodyValue(res))
//                    .onErrorResume(e -> ServerResponse.badRequest().bodyValue(e.getMessage()));
//        }
//
//        public Mono<ServerResponse> deleteBook(ServerRequest request) {
//            return request.bodyToMono(String.class)
//                    .flatMap(body ->
//                            webClient.post()
//                                    .uri("/books/delete")
//                                    .bodyValue(body)
//                                    .retrieve()
//                                    .bodyToMono(String.class)
//                    )
//                    .flatMap(res -> ServerResponse.ok().bodyValue(res))
//                    .onErrorResume(e -> ServerResponse.badRequest().bodyValue(e.getMessage()));
//        }
//
//        public Mono<ServerResponse> updateBook(ServerRequest request) {
//            return request.bodyToMono(String.class)
//                    .flatMap(body ->
//                            webClient.post()
//                                    .uri("/books/update")
//                                    .bodyValue(body)
//                                    .retrieve()
//                                    .bodyToMono(String.class)
//                    )
//                    .flatMap(res -> ServerResponse.ok().bodyValue(res))
//                    .onErrorResume(e -> ServerResponse.badRequest().bodyValue(e.getMessage()));
//        }
//
//        public Mono<ServerResponse> getBooks(ServerRequest request) {
//            String title = request.queryParam("name").orElse("");
//            String ibsn = request.queryParam("ibsno").orElse("");
//
//            return webClient.get()
//                    .uri(uriBuilder -> uriBuilder
//                            .path("/books/getBooks")
//                            .queryParam("name", title)
//                            .queryParam("ibsno", ibsn)
//                            .build())
//                    .retrieve()
//                    .bodyToMono(String.class)
//                    .flatMap(body -> ServerResponse.ok().bodyValue(body))
//                    .onErrorResume(e -> ServerResponse.badRequest().bodyValue(e.getMessage()));
//        }
//
//
//}
