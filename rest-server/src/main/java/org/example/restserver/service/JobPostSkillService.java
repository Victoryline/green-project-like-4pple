package org.example.restserver.service;

import lombok.RequiredArgsConstructor;
import org.example.restserver.dto.GubunDto;
import org.example.restserver.dto.JobPostDto;
import org.example.restserver.entity.Gubun;
import org.example.restserver.entity.JobPost;
import org.example.restserver.entity.JobPostSkill;
import org.example.restserver.entity.User;
import org.example.restserver.repository.GubunRepository;
import org.example.restserver.repository.JobPostSkillRepository;
import org.example.restserver.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
@RequiredArgsConstructor
public class JobPostSkillService {

    private final JobPostSkillRepository jobPostSkillRepository;
    private final GubunRepository gubunRepository;
    private final UserRepository userRepository;

    public List<JobPost> getJobPostsBySkillCode(String skillCode) {
        return jobPostSkillRepository.findJobPostsBySkillCode(skillCode);
    }

    public List<GubunDto> getSkills() {
        List<Gubun> skills = gubunRepository.findAllByIdGubunCode("SKILL");
        List<GubunDto> skillDtos = new ArrayList<>();

        for (Gubun skill : skills) {
            GubunDto skillDto = new GubunDto(skill.getId().getGubunCode(), skill.getId().getCode(), skill.getName());
            skillDtos.add(skillDto);
        }
        return skillDtos;
    }

    public List<JobPostDto> getJobPostsBySkills(List<String> skillCodes) {
        List<JobPost> jobPosts;

        if (skillCodes.isEmpty()) {
            jobPosts = jobPostSkillRepository.findAllJobPosts();
        } else {
            jobPosts = jobPostSkillRepository.findJobPostsBySkillCodes(skillCodes);
        }

        List<JobPostDto> jobPostDtos = new ArrayList<>();
        for (JobPost jobPost : jobPosts) {
            jobPostDtos.add(convertToDto(jobPost));
        }

        return jobPostDtos;
    }

    public List<JobPostDto> getAllJobPosts() {
        List<JobPost> jobPosts = jobPostSkillRepository.findAllJobPosts();
        List<JobPostDto> jobPostDtos = new ArrayList<>();
        for (JobPost jobPost : jobPosts) {
            jobPostDtos.add(convertToDto(jobPost));
        }
        return jobPostDtos;
    }

    private JobPostDto convertToDto(JobPost jobPost) {
        JobPostDto dto = new JobPostDto();
        dto.setTitle(jobPost.getTitle());

        // User 테이블에서 name 조회
        String companyName = "회사명 없음";
        if (jobPost.getCompany() != null) {
            String username = jobPost.getCompany().getUsername();
            if (username != null) {
                User user = userRepository.findById(username).orElse(null);
                if (user != null) {
                    companyName = user.getName();
                }
            }
        }
        dto.setUsername(companyName);

        dto.setAddress(jobPost.getCompany() != null ? jobPost.getCompany().getAddress() : "주소 없음");
        dto.setJobHistory(jobPost.getJobHistory());

        List<String> skillList = new ArrayList<>();
        for (JobPostSkill skill : jobPost.getJobPostSkills()) {
            skillList.add(skill.getId().getSkillCode());
        }
        dto.setJobPostSkills(skillList);

        return dto;
    }
}
