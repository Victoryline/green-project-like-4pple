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
    private final JwtUtil jwtUtil;

    @GetMapping("/{companyId}")
    public ResponseEntity<List<CompanyScore>> getCompanyScore(@PathVariable("companyId") String companyId) {
        List<CompanyScore> companyScores = companyScoreService.getCompanyScores(companyId);
        return ResponseEntity.ok(companyScores);
    }

    @PostMapping("/rating")
    public ResponseEntity<?> addCompanyScore(
            @RequestBody CompanyScoreDto companyScoreDto,
            HttpServletRequest request) {
//        String token = request.getHeader("Authorization");
//        if (token == null || !token.startsWith("Bearer ")) {
//            return ResponseEntity.status(403).body("구직자만 등록할 수 있습니다.");
//        }
//
//        String userRole = jwtUtil.getUserRole(token.substring(7));
//        if (!"ROLE_JOB_SEEKER".equals(userRole)) {
//            return ResponseEntity.status(403).body("구직자만 등록할 수 있습니다.");
//        }

        if (companyScoreDto.getScore() < 1 || companyScoreDto.getScore() > 5) {
            return ResponseEntity.status(400).body("평점은 1~5 사이의 값이어야 합니다.");
        }

        companyScoreService.addScore(companyScoreDto);
        return ResponseEntity.ok("등록되었습니다.");
    }
}
