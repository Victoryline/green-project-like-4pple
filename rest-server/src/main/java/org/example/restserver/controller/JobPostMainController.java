package org.example.restserver.controller;

import lombok.RequiredArgsConstructor;
import org.example.restserver.dto.MainJobPostDto;
import org.example.restserver.repository.JobPostRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * packageName    : org.example.restserver.controller
 * fileName       : JobPostMainController
 * author         : 황승현
 * date           : 2025-01-03
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-01-03        황승현       최초 생성
 */
@RestController
@RequestMapping("/api/v1/job-post")
@RequiredArgsConstructor
public class JobPostMainController {
    private final JobPostRepository jobPostRepository;

    @GetMapping("/popular")
    public List<MainJobPostDto> getPopular() {
        return jobPostRepository.findPopularJobPostsWithCompanyInfo();
    }

    @GetMapping("/main/{username}")
    public List<MainJobPostDto> getJobPosts(@PathVariable String username) {
        return jobPostRepository.findJobPostsWithCompanyInfo(username);
    }
}
