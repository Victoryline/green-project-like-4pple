package org.example.restserver.controller;

import lombok.RequiredArgsConstructor;
import org.example.restserver.dto.JobPostDto;
import org.example.restserver.dto.JobPostResponseDto;
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
    public List<JobPostResponseDto> getAllJobPostsWithCompany() {
        List<JobPostResponseDto> jobPosts = jobPostService.getAllJobPostsWithCompany();
        System.out.println("jobPosts size: " + jobPosts.size());
        //System.out.println(jobPosts.get(1));
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

    @PutMapping("/updateForm")
    public ResponseEntity<?> updateForm(@RequestBody JobPostDto jobPost) {
        System.out.println("수정 여기 옵니꺼");

        jobPostService.modify(jobPost);
        System.out.println(jobPost);
        return ResponseEntity.ok("수정 성공");
    }

    @DeleteMapping("/deleteForm/{jobPostNo}")
    public ResponseEntity<?> deleteform(@PathVariable Integer jobPostNo) {
        System.out.println("삭제 번호 : "+jobPostNo);

        jobPostService.delete(jobPostNo);
        return ResponseEntity.ok("삭제 성공");
    }


}