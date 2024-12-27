package org.example.viewserver.utils;

import org.example.viewserver.common.ApiResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class WebClientUtil {

    private final WebClient webClient;

    public WebClientUtil(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8080").build();
    }

    // GET 요청
    public Mono<ApiResponse> get(String endpoint) {
        return webClient.get()
                .uri(endpoint)
                .retrieve()
                .bodyToMono(ApiResponse.class)
                .doOnError(e -> System.err.println("Error in WebClientUtil GET: " + e.getMessage()));
    }
}
