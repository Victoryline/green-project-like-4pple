package org.example.restserver.controller;

import lombok.RequiredArgsConstructor;
import org.example.restserver.dto.JobPostDto;
import org.example.restserver.entity.Company;
import org.example.restserver.entity.JobPost;
import org.example.restserver.service.JobPostServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public ResponseEntity<List<JobPostDto>> getAllJobPostsWithCompany() {
        // 서비스에서 채용공고와 기업정보를 가져와서 반환
        List<JobPostDto> jobPosts = jobPostService.getAllJobPostsWithCompany();
        return ResponseEntity.ok(jobPosts);
    }
}
