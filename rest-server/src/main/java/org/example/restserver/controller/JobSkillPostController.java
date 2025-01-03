package org.example.restserver.controller;

import lombok.RequiredArgsConstructor;
import org.example.restserver.dto.GubunDto;
import org.example.restserver.dto.JobPostDto;
import org.example.restserver.entity.JobPost;
import org.example.restserver.service.JobPostService;
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
    public ResponseEntity<List<JobPost>> getJobPostsBySkill(@PathVariable String skillCode) {
        List<JobPost> jobPosts = jobPostSkillService.getJobPostsBySkillCode(skillCode);
        //System.out.println(jobPosts);
        return ResponseEntity.ok(jobPosts);
    }

    @PostMapping("/skills/match")
    public ResponseEntity<List<JobPost>> getJobPostsBySkills(@RequestBody List<String> skillCodes) {
        List<JobPost> jobPosts = jobPostSkillService.getJobPostsBySkillCodes(skillCodes);
        return ResponseEntity.ok(jobPosts);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<JobPostDto>> getFilteredJobPosts(@RequestParam List<String> skills) {
        List<JobPostDto> filteredPosts = jobPostSkillService.getJobPostsBySkills(skills);
        System.out.println("Filtered Posts: " + filteredPosts);
        return ResponseEntity.ok(filteredPosts);
    }

    @GetMapping("/all")
    public ResponseEntity<List<JobPostDto>> getAllJobPosts() {
        List<JobPostDto> allPosts = jobPostSkillService.getAllJobPosts();
        return ResponseEntity.ok(allPosts);
    }
}