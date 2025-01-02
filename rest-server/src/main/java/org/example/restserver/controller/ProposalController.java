package org.example.restserver.controller;

import lombok.RequiredArgsConstructor;
import org.example.restserver.common.ApiResponse;
import org.example.restserver.dto.ProposalRequestDto;
import org.example.restserver.dto.ProposalResponseDto;
import org.example.restserver.entity.Proposal;
import org.example.restserver.service.ProposalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * packageName    : org.example.restserver.controller
 * fileName       : ProposalController
 * author         : 이동하
 * date           : 2024-12-27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-27        이동하       최초 생성
 */
@RestController
@RequestMapping("/proposal")
@RequiredArgsConstructor
public class ProposalController {

    private final ProposalService proposalService;

    @GetMapping("/{proposalId}")
    public ResponseEntity<ApiResponse<ProposalResponseDto>> getProposalById(@PathVariable Integer proposalId) {
        ProposalResponseDto proposal = proposalService.getProposalById(proposalId);
        return ResponseEntity.ok(new ApiResponse<>("SUCCESS", proposal));
    }

    @GetMapping("/jobseeker/{userId}")
    public ResponseEntity<ApiResponse<List<ProposalResponseDto>>> getProposalsByUser(@PathVariable String userId) {
        List<ProposalResponseDto> userProposals = proposalService.getProposalsByUser(userId);
        return ResponseEntity.ok(new ApiResponse<>("SUCCESS", userProposals));
    }

    @GetMapping("/company/{companyId}/unread")
    public ResponseEntity<ApiResponse<List<ProposalResponseDto>>> getUnreadProposalsByCompany(@PathVariable String companyId) {
        List<ProposalResponseDto> unreadProposals = proposalService.getUnreadProposalsByCompany(companyId);
        return ResponseEntity.ok(new ApiResponse<>("SUCCESS", unreadProposals));
    }
}