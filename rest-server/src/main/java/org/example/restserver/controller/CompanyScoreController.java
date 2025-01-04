package org.example.restserver.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.restserver.dto.CompanyScoreDto;
import org.example.restserver.entity.CompanyScore;
import org.example.restserver.service.CompanyScoreService;
import org.example.restserver.utils.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * packageName    : org.example.restserver.dto
 * fileName       : CompanyScoreController
 * author         : 이동하
 * date           : 2024-12-31
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-31        이동하       최초 생성
 */

@RestController
@RequestMapping("/api/v1/scores")
@RequiredArgsConstructor
public class CompanyScoreController {

    private final CompanyScoreService companyScoreService;

    @GetMapping("/{username}")
    public ResponseEntity<List<CompanyScore>> getCompanyScore(@PathVariable("username") String username) {
        List<CompanyScore> companyScores = companyScoreService.getCompanyScores(username);
        return ResponseEntity.ok(companyScores);
    }

    @PostMapping("/rating")
    public ResponseEntity<String> addCompanyScore(@RequestBody CompanyScoreDto scoreDto) {
        if (scoreDto.getJobSeekerId() == null) {
            throw new IllegalArgumentException("Job seeker ID is required.");
        }
        companyScoreService.addScore(scoreDto);
        return ResponseEntity.ok("평점이 성공적으로 등록되었습니다.");
    }
}
