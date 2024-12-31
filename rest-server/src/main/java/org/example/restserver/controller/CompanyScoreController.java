package org.example.restserver.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.restserver.entity.CompanyScore;
import org.example.restserver.service.CompanyScoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CompanyScoreController {

    private final CompanyScoreService companyScoreService;

    @GetMapping("/score/{companyId}")
    public ResponseEntity<List<CompanyScore>> getCompanyScore(@PathVariable("companyId") String companyId) {
        List<CompanyScore> companyScores = companyScoreService.getCompanyScores(companyId);
        return ResponseEntity.ok(companyScores);
    }
}
