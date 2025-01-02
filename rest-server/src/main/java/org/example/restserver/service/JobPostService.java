package org.example.restserver.service;

import org.example.restserver.dto.JobPostDto;
import org.example.restserver.entity.JobPost;

import java.util.Optional;

public interface JobPostService {
    void register (JobPostDto jobPostDto);
    JobPostDto getJobPostDetailById(Integer jobpostno);
}
