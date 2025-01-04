package org.example.restserver.controller;

import lombok.RequiredArgsConstructor;
import org.example.restserver.dto.GubunDto;
import org.example.restserver.dto.JobPostDto;
import org.example.restserver.dto.JobPostResponseDto;
import org.example.restserver.entity.JobPost;
import org.example.restserver.service.JobPostSkillService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * packageName    : org.example.restserver.controller
 * fileName       : JobSkillController
 * author         : 이동하
 * date           : 2024-12-27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-27        이동하       최초 생성
 */

@RestController
@RequestMapping("/api/v1/jobpost")
@RequiredArgsConstructor
public class JobSkillPostController {

    private final JobPostSkillService jobPostSkillService;

    @GetMapping("/skills")
    public ResponseEntity<List<GubunDto>> getSkills() {
        List<GubunDto> skills = jobPostSkillService.getSkills();
        return ResponseEntity.ok(skills);
    }

    @GetMapping("/skill/{skillCode}")
    public ResponseEntity<List<JobPostResponseDto>> getJobPostsBySkill(@PathVariable String skillCode) {
        List<JobPostResponseDto> jobPosts = jobPostSkillService.getJobPostsBySkillCode(skillCode);
        return ResponseEntity.ok(jobPosts);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<JobPostResponseDto>> filterJobPosts(@RequestParam List<String> skills) {
        List<JobPostResponseDto> filteredPosts = jobPostSkillService.getJobPostsBySkills(skills);
        return ResponseEntity.ok(filteredPosts);
    }

    @GetMapping("/all")
    public ResponseEntity<List<JobPostResponseDto>> getAllJobPosts() {
        List<JobPostResponseDto> allPosts = jobPostSkillService.getAllJobPosts();
        return ResponseEntity.ok(allPosts);
    }
}