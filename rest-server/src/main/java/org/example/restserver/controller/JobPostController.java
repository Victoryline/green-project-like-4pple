package org.example.restserver.controller;

import lombok.RequiredArgsConstructor;
import org.example.restserver.dto.JobPostDto;
import org.example.restserver.dto.JobPostResponseDto;
import org.example.restserver.service.JobPostServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/job-post")
@RequiredArgsConstructor
public class JobPostController {

    private final JobPostServiceImpl jobPostService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody JobPostDto jobPost) {
        System.out.println("여기 옵니꺼");
        System.out.println(jobPost);
        jobPostService.register(jobPost);
         return ResponseEntity.ok("등록 성공");
    }

    @GetMapping("/list")
    public List<JobPostResponseDto> getAllJobPostsWithCompany() {
        List<JobPostResponseDto> jobPosts = jobPostService.getAllJobPostsWithCompany();

        System.out.println("jobPosts size: " + jobPosts.size());
        return jobPosts;
    }

}