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
    public ResponseEntity<String> getAllJobPostsWithCompany() {
        List<Object[]> jobPosts = jobPostService.getAllJobPostsWithCompany();

        System.out.println("jobPosts size: " + jobPosts.size());

        jobPosts.forEach(jobPost -> {
            System.out.println("Title: " + jobPost[0]);
            System.out.println("JobPostSkills: " + jobPost[1]);
            System.out.println("Company Username: " + jobPost[2]);
            System.out.println("Company Address: " + jobPost[3]);
        });


        return  ResponseEntity.ok("리스트 성공");
    }
}