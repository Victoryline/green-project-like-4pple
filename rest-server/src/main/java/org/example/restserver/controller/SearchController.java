package org.example.restserver.controller;

import lombok.RequiredArgsConstructor;
import org.example.restserver.dto.CompanyScoreDto;
import org.example.restserver.dto.CompanySearchDto;
import org.example.restserver.dto.JobPostSearchDto;
import org.example.restserver.service.CompanyScoreService;
import org.example.restserver.service.SearchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * packageName    : org.example.restserver.controller
 * fileName       : SerchController
 * author         : 이동하
 * date           : 2024-12-30
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-30        이동하       최초 생성
 */

@RestController
@RequestMapping("/api/v1/search")
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;
    private final CompanyScoreService companyScoreService;

    @GetMapping
    public ResponseEntity<?> search(@RequestParam String type, @RequestParam(required = false) String keyword) {

        keyword = (keyword == null) ? "" : keyword.trim();

        if ("company".equals(type)) {
            List<CompanySearchDto> companies = searchService.searchCompanies(keyword);
            return ResponseEntity.ok(companies);
        } else if ("job".equals(type)) {
            List<JobPostSearchDto> jobPosts = searchService.searchJobPostsByKeyword(keyword);
            return ResponseEntity.ok(jobPosts);
        } else {
            return ResponseEntity.badRequest().body("유효하지 않은 검색 타입입니다.");
        }
    }

    @PostMapping
    public ResponseEntity<String> addCompanyScore(@RequestBody CompanyScoreDto companyScoreDto) {
        try {
            companyScoreService.addScore(companyScoreDto);
            return ResponseEntity.ok("평점이 성공적으로 등록되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("뭔가 잘못됨.");
        }
    }


}