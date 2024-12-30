package org.example.restserver.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.restserver.dto.ResumeRequestDto;
import org.example.restserver.service.ResumeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * packageName    : org.example.restserver.controller
 * fileName       : ResumeRestController
 * author         : 김재홍
 * date           : 24. 12. 30.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 12. 30.        김재홍       최초 생성
 */
@RestController
@RequiredArgsConstructor
@Slf4j
public class ResumeRestController {

    final private ResumeService resumeService;

    @PostMapping("/resume")
    public int postResume(ResumeRequestDto resumeDto) {
        log.debug("Post Resume" + resumeDto);

        return resumeService.postResume(resumeDto);
    }

}
