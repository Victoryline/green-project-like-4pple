package org.example.restserver.controller;

import org.example.restserver.entity.JobPost;
import org.example.restserver.service.JobPostSkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/jobpost")
public class JobSkillPostController {

    @Autowired
    JobPostSkillService jobPostSkillService;

    @GetMapping("/skill/{skillCode}")
    public ResponseEntity<List<JobPost>> getJobPostsBySkill(@PathVariable String skillCode) {
        List<JobPost> jobPosts = jobPostSkillService.getJobPostsBySkillCode(skillCode);
        System.out.println(jobPosts);
        return ResponseEntity.ok(jobPosts);
    }
}