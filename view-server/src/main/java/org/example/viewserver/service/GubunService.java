package org.example.viewserver.service;


import org.example.viewserver.dto.GubunDto;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class GubunService {

    private final WebClient webClient;

    public GubunService(WebClient webClient) {
        this.webClient = webClient;
    }

    public List<GubunDto> getGubunList() {
        // 레스트 서버 API 호출
        return webClient.get()
                .uri("api/gubun/getgubun")
                .retrieve()
                .bodyToFlux(GubunDto.class)
                .collectList()
                .block();
    }


}