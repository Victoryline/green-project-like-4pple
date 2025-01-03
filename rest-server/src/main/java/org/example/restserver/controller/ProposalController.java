package org.example.restserver.controller;

import lombok.RequiredArgsConstructor;
import org.example.restserver.common.ApiResponse;
import org.example.restserver.dto.ProposalRequestDto;
import org.example.restserver.dto.ProposalResponseDto;
import org.example.restserver.entity.Proposal;
import org.example.restserver.entity.Resume;
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
@RequestMapping("/api/v1/proposal")
@RequiredArgsConstructor
public class ProposalController {

    private final ProposalService proposalService;

    //기업이 북마크한 구직자 리스트 보기
    @GetMapping("/bookmarked/{companyId}")
    public ResponseEntity<List<String>> getBookmarkedJobSeeker(@PathVariable String companyId) {
        List<String> bookmarkedJobSeeker = proposalService.getBookmarkedJobSeeker(companyId);
        return ResponseEntity.ok(bookmarkedJobSeeker);
    }

    //북마크한 전체 구직자의 이력서 조회
    @GetMapping("/bookmarked/resumes/{companyId}")
    public ResponseEntity<List<Resume>> getResumes(@PathVariable String companyId) {
        List<Resume> resumes = proposalService.getResumes(companyId);
        return ResponseEntity.ok(resumes);
    }

    //하나의 이력서 상세정보 조회
    @GetMapping("/resume/{resumeId}")
    public ResponseEntity<Resume> getResumeDetails(@PathVariable Integer resumeId) {
        Resume resumeDetails = proposalService.getResumeDetails(resumeId);
        return ResponseEntity.ok(resumeDetails);
    }

}