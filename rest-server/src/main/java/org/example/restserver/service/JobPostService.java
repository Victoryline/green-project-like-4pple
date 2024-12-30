package org.example.restserver.service;

import org.example.restserver.dto.JobPostDto;
import org.example.restserver.dto.UserRequestDto;

public interface JobPostService {
    void register (JobPostDto jobPostDto);
}
