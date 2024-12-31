package org.example.viewserver.service;

import lombok.RequiredArgsConstructor;
import org.example.viewserver.dto.ProposalDto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

/**
 * packageName    : org.example.viewserver.service
 * fileName       : ProposalService
 * author         : 이동하
 * date           : 2024-12-28
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-28        이동하       최초 생성
 */

@Service
@RequiredArgsConstructor
public class ProposalService {

    private final WebClient webClient;

    public List<ProposalDto> getProposals() {
        return webClient.get()
                .uri("/api/v1/proposals")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<ProposalDto>>() {})
                .block();
    }
}