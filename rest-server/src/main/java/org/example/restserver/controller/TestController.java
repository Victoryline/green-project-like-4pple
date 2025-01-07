package org.example.restserver.controller;

import org.example.restserver.entity.Company;
import org.example.restserver.entity.JobPost;
import org.example.restserver.repository.JobPostRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * Created on 2024-12-25 by 황승현
 */
@RestController
public class TestController {
    private final JobPostRepository jobPostRepository;

    public TestController(JobPostRepository jobPostRepository) {
        this.jobPostRepository = jobPostRepository;
    }

    @GetMapping("/api/v1/test")
    public String test() {
        return "메세지~~~";
    }

    @GetMapping("/api/v1/test2")
    public void test2() {
        JobPost jobPost = jobPostRepository.findById(32).orElse(null);
//        Company company = jobPost.getCompany();

        System.out.println("모집공고 : " + jobPost + "기업 : " + jobPost.getCompany().toString());
//        System.out.println("기업" + company);
    }
}


