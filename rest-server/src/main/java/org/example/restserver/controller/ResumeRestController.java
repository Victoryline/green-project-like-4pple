package org.example.restserver.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.restserver.dto.GubunDto;
import org.example.restserver.dto.JobSeekerUserResponseDto;
import org.example.restserver.dto.ResumeRequestDto;
import org.example.restserver.service.GubunService;
import org.example.restserver.service.ResumeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
@RequestMapping("/api/v1/resume")
public class ResumeRestController {

    final private ResumeService resumeService;
    private final GubunService gubunService;

    @GetMapping
    public List<GubunDto> getResume() {

        return gubunService.getGubunList("skill");

    }

    @GetMapping("/{username}")
    public ResponseEntity<JobSeekerUserResponseDto> getResumeById(@PathVariable String username) {
        return ResponseEntity.ok(resumeService.getUserInfo(username));
    }

    @PostMapping
    public int postResume(@RequestBody ResumeRequestDto resumeDto) {
        log.debug("Post Resume{}", resumeDto);

        return resumeService.postResume(resumeDto);
    }

}
