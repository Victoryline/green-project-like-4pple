package org.example.restserver.controller;

import lombok.RequiredArgsConstructor;
import org.example.restserver.dto.JobPostDto;
import org.example.restserver.entity.JobPost;
import org.example.restserver.service.JobPostServiceImpl;
import org.springframework.http.HttpStatus;
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

    @GetMapping("list")
    public ResponseEntity<?> getAll() {
        List<JobPost> jobPosts = jobPostService.getlist();
        if (jobPosts.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("리스트가 비어 있습니다.");
        }
        return ResponseEntity.ok(jobPosts);
    }
}
