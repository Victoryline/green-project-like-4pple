package org.example.restserver.controller;

import lombok.RequiredArgsConstructor;
import org.example.restserver.dto.ApplyResumeResponseDTO;
import org.example.restserver.repository.ApplyResumeRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created on 2025-01-04 by 황승현
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/apply-resumes")
public class ApplyResumeController {
    private final ApplyResumeRepository applyResumeRepository;

    @GetMapping("/{jobPostNo}")
    public List<ApplyResumeResponseDTO> getApplyResumeByJobPostNo(@PathVariable("jobPostNo") int jobPostNo) {
        System.out.println(jobPostNo);
        return applyResumeRepository.getApplicationsByJobPostNo(jobPostNo);
    }

    @PutMapping("/{jobPostNo}")
    public int setPassYn(@PathVariable int jobPostNo, @RequestParam("resumeNo") int resumeNo, @RequestParam("passYn") Character passYn) {
        return applyResumeRepository.updatePassYnByJobPostNoAndResumeNo(jobPostNo, resumeNo, passYn);
    }
}
