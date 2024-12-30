package org.example.restserver.controller;

import lombok.RequiredArgsConstructor;
import org.example.restserver.dto.JobPostDto;
import org.example.restserver.service.JobPostServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
