package org.example.viewserver.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class TestServiceImpl implements TestService {

    private final WebClient webClient;

    public TestServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public String getTestMessage() {
        return webClient.get()
                .uri("/api/v1/test")
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
