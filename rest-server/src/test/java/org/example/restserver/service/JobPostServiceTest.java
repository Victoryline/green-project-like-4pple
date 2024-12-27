package org.example.restserver.service;

import jakarta.transaction.Transactional;
import org.example.restserver.entity.JobPost;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
@SpringBootTest
@Transactional
public class JobPostServiceTest {

    @Autowired
    private JobPostRepository jobPostRepository;

    @Test
    void testFindBySkillCode(){
        String skillCode = "CSS";
        List<JobPost> jobPosts = jobPostRepository.findBySkillCode(skillCode);
        System.out.println(jobPosts);
    }

}