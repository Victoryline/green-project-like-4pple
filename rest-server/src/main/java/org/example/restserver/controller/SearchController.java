package org.example.restserver.controller;

import org.example.restserver.dto.CompanySearchDto;
import org.example.restserver.dto.JobPostSearchDto;
import org.example.restserver.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
public class SearchController {

    @Autowired
    private SearchService searchService;

    @GetMapping
    public ResponseEntity<?> search(@RequestParam String type, @RequestParam String keyword) {
        System.out.println("type: " + type + " keyword: " + keyword);

        if (keyword == null || keyword.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("검색어를 입력하세요.");
        }


        if ("company".equals(type)) {
            List<CompanySearchDto> companies = searchService.searchCompanies(keyword);
            return ResponseEntity.ok(companies);
        } else if ("job".equals(type)) {
            List<JobPostSearchDto> jobPosts = searchService.searchJobPosts(keyword);
            return ResponseEntity.ok(jobPosts);
        } else {
            return ResponseEntity.badRequest().body("유효하지 않은 검색 타입입니다.");
        }
    }
}
