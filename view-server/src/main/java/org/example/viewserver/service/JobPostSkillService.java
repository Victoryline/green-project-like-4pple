package org.example.viewserver.service;

import lombok.RequiredArgsConstructor;
import org.example.viewserver.common.ApiResponse;
import org.example.viewserver.dto.JobPostSkillResponseDto;
import org.example.viewserver.utils.WebClientManager;
import org.springframework.stereotype.Service;

/**
 * packageName    : org.example.viewserver.service
 * fileName       : JobPostSkillService
 * author         : 이동하
 * date           : 2025-01-05
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-01-05        이동하       최초 생성
 */

@Service
@RequiredArgsConstructor
public class JobPostSkillService {

    private final WebClientManager webClientManager;

    public JobPostSkillResponseDto getJobPostSkillDetail(Integer id) {
        ApiResponse apiResponse = webClientManager.get("/api/v1/jobpost/detail/" + id);

        return (JobPostSkillResponseDto) apiResponse.getBody();
    }
}