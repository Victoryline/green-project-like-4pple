package org.example.viewserver.service;

import lombok.RequiredArgsConstructor;
import org.example.viewserver.utils.WebClientUtil;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final WebClientUtil webClientUtil;

    @Override
    public Mono<Object> getTestMessage() {
        return webClientUtil.get("/api/v1/test")
                .map(response -> {
                    if (response.getBody() != null) {
                        return response.getBody();
                    } else {
                        return "데이터 없음";
                    }
                });
//                .onErrorResume(e -> {
//                    System.err.println("에러 메세지 " + e.getMessage());
//                    return Mono.just("Error: " + e.Message());
//                });
    }

}
