package org.example.viewserver.service;

import org.apache.coyote.Response;

import org.example.viewserver.dto.JobPostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.reactive.function.client.WebClient;

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
 public class JobpostServiceImpl implements JobpostService {

    private WebClient webClient;

    @Override
    public ResponseEntity<?> regist(JobPostDto jobpost) {
        webClient.post()
                 .uri("api-jobpost/regist")
                .bodyValue(jobpost)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
        return ResponseEntity.status(HttpStatus.OK).body("게시글 등록 성공");

    }
}
