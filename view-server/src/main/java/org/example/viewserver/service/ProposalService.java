package org.example.viewserver.service;

import lombok.RequiredArgsConstructor;
import org.example.viewserver.dto.ProposalDto;
import org.example.viewserver.utils.WebClientManager;
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

//    private final WebClientManager webClientManager;
//
//    public List<ProposalDto> getProposalsForCompany(String companyId) {
//
//
//            return webClient.get()
//                .uri(uriBuilder -> uriBuilder
//                        .path("/api/v1/proposals")
//                        .queryParam("companyId", companyId)
//                        .build())
//                .retrieve()
//                .bodyToMono(new ParameterizedTypeReference<List<ProposalDto>>() {})
//                .block();
//    }
}