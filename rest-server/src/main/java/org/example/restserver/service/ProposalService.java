package org.example.restserver.service;

import lombok.RequiredArgsConstructor;
import org.example.restserver.dto.ProposalRequestDto;
import org.example.restserver.dto.ProposalResponseDto;
import org.example.restserver.entity.Proposal;
import org.example.restserver.repository.ProposalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * packageName    : org.example.restserver.service
 * fileName       : ProposalService
 * author         : 이동하
 * date           : 2024-12-27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-27        이동하       최초 생성
 */

@Service
@RequiredArgsConstructor
public class ProposalService {

    private final ProposalRepository proposalRepository;

    // 1. 제안서 생성
    public void createProposal(ProposalRequestDto request) {
        Proposal proposal = Proposal.builder()
                .companyId(request.getCompanyId())
                .userId(request.getUserId())
                .title(request.getTitle())
                .content(request.getContent())
                .phone(request.getPhone())
                .email(request.getEmail())
                .sendDate(Instant.now())
                .build();
        proposalRepository.save(proposal);
    }

    // 2. 단일 제안서 조회 (ID로)
    public ProposalResponseDto getProposalById(Integer proposalId) {
        Proposal proposal = proposalRepository.findById(proposalId)
                .orElseThrow(() -> new RuntimeException("Proposal not found with ID: " + proposalId));
        return convertToDto(proposal);
    }

    // 3. 특정 유저가 받은 모든 제안서 조회
    public List<ProposalResponseDto> getProposalsByUser(String userId) {
        List<Proposal> userProposals = proposalRepository.findByUserId(userId);
        return userProposals.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // 4. 읽음 처리
    public void markAsRead(Integer proposalId) {
        Proposal proposal = proposalRepository.findById(proposalId)
                .orElseThrow(() -> new RuntimeException("Proposal not found with ID: " + proposalId));

        if (proposal.getReadDate() == null) {
            proposal.setReadDate(Instant.now());
            proposalRepository.save(proposal);
        }
    }

    // 5. 읽지 않은 제안서 조회 (기업 ID로)
    public List<ProposalResponseDto> getUnreadProposalsByCompany(String companyId) {
        List<Proposal> unreadProposals = proposalRepository.findUnreadProposalsByCompanyId(companyId);
        return unreadProposals.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // Proposal -> ProposalResponseDto 변환
    private ProposalResponseDto convertToDto(Proposal proposal) {
        return new ProposalResponseDto(
                proposal.getId(),
                proposal.getCompanyId(),
                proposal.getUserId(),
                proposal.getTitle(),
                proposal.getContent(),
                proposal.getSendDate(),
                proposal.getReadDate()
        );
    }
}