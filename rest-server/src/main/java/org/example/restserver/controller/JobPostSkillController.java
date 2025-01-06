package org.example.restserver.controller;

import lombok.RequiredArgsConstructor;
import org.example.restserver.dto.GubunDto;
import org.example.restserver.dto.JobPostResponseDto;
import org.example.restserver.dto.JobPostSkillResponseDto;
import org.example.restserver.service.JobPostSkillService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/jobpost")
@RequiredArgsConstructor
public class JobPostSkillController {

    private final JobPostSkillService jobPostSkillService;

    @GetMapping("/skills")
    public ResponseEntity<List<GubunDto>> getSkills() {
        List<GubunDto> skills = jobPostSkillService.getSkills();
        return ResponseEntity.ok(skills);
    }

    @GetMapping("/skill/{skillCode}")
    public ResponseEntity<List<JobPostSkillResponseDto>> getJobPostsBySkillCode(@PathVariable String skillCode) {
        List<JobPostSkillResponseDto> jobPostSkills = jobPostSkillService.getJobPostsBySkillCode(skillCode);
        return ResponseEntity.ok(jobPostSkills);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<JobPostSkillResponseDto>> filterJobPostsBySkills(@RequestParam List<String> skills) {
        List<JobPostSkillResponseDto> filteredJobPostSkills = jobPostSkillService.filterJobPostsBySkills(skills);
        return ResponseEntity.ok(filteredJobPostSkills);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<JobPostSkillResponseDto> getJobPostSkillDetail(@PathVariable Integer id) {
        JobPostSkillResponseDto detail = jobPostSkillService.getDetailById(id);
        return ResponseEntity.ok(detail);
    }

    @GetMapping("/all")
    public ResponseEntity<List<JobPostSkillResponseDto>> getAllJobPosts() {
        List<JobPostSkillResponseDto> allPosts = jobPostSkillService.getAllJobPosts();
        return ResponseEntity.ok(allPosts);
    }

}
