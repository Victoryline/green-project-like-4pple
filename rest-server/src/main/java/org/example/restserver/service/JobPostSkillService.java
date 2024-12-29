package org.example.restserver.service;

import org.example.restserver.entity.JobPost;
import org.example.restserver.repository.JobPostSkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * packageName    : org.example.restserver.service
 * fileName       : JobPostService
 * author         : 이동하
 * date           : 2024-12-26
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-26        이동하       최초 생성
 */
@Service
public class JobPostSkillService {

    @Autowired
    private JobPostSkillRepository jobPostSkillRepository;

    public List<JobPost> getJobPostsBySkillCode(String skillCode) {
        return jobPostSkillRepository.findJobPostsBySkillCode(skillCode);
    }
}