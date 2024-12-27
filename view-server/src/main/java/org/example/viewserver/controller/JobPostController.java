package org.example.viewserver.controller;

import org.example.viewserver.dto.JobPostDto;
import org.example.viewserver.service.JobpostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/job-post")
public class JobPostController {

    @Autowired
    JobpostServiceImpl jobpostService;

    @GetMapping("/regist")
    public String jobPost() {
        return "job-post/jobpostRegistForm";
    }
/*
    @PostMapping("/regist")
    public String regist(JobPostDto jobpostdto) {
            jobpostService.save(jobpostdto);
        return "job-post/regist";
    }*/
}
