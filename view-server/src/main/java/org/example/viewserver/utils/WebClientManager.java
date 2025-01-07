package org.example.viewserver.utils;

import org.example.viewserver.common.ApiResponse;
import org.example.viewserver.exceptions.ForbiddenException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class WebClientManager {

    private final WebClient webClient;
    private final TokenManager tokenManager;

    public WebClientManager(WebClient.Builder webClientBuilder, TokenManager tokenManager) {
        this.webClient = webClientBuilder.baseUrl("http://192.168.0.15:8080").build();
        this.tokenManager = tokenManager;
    }

    // GET 요청
    public ApiResponse get(String endpoint, Object... uriVariables) {
        return webClient.get()
                .uri(endpoint, uriVariables) // PathVariable 처리
                .header("Authorization", "Bearer " + tokenManager.getTokenByCookie())
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> {
                    if (clientResponse.statusCode() == HttpStatus.FORBIDDEN) {
                        return Mono.error(new ForbiddenException("권한 부족 (403 Forbidden)"));
                    }
                    return Mono.error(new RuntimeException("클라이언트 오류: " + clientResponse.statusCode()));
                })
                .bodyToMono(ApiResponse.class)
                .doOnError(e -> System.err.println("Error in WebClientUtil GET: " + e.getMessage()))
                .block();
    }
}
