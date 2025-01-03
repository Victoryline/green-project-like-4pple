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

    @GetMapping("/detail/{jobpostno}")
    public ResponseEntity<JobPostDto> getJobPostDetail(@PathVariable Integer jobpostno) {
        JobPostDto jobPostDto = jobPostService.getJobPostDetailById(jobpostno);
        System.out.println("jobPostDto: " + jobPostDto);
        if (jobPostDto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(jobPostDto);
    }








}