package org.example.viewserver.service;



import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.viewserver.common.ApiResponse;
import org.example.viewserver.dto.JobPostDto;
import org.example.viewserver.utils.WebClientManager;
import org.springframework.stereotype.Service;


/**
 *packageName    : org.example.viewserver.service
 * fileName       : JobpostServiceImpl
 * author         : 박미정
 * date           : 24. 12. 26.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 12. 26.        박미정      최초 생성
 */
@Service
@RequiredArgsConstructor
 public class JobpostServiceImpl implements JobpostService {

    private final WebClientManager webClientManager;

    private final ObjectMapper objectMapper;

    public JobPostDto getJobPostDetail(Integer jobpostno) {

        ApiResponse response = webClientManager.get("/api/v1/job-post/detail/{jobpostno}", jobpostno);
        JobPostDto jobPostDto = objectMapper.convertValue(response.getBody(), JobPostDto.class);

        return jobPostDto;
    }
}


