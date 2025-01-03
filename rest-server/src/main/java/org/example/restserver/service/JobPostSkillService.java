package org.example.restserver.service;

import lombok.RequiredArgsConstructor;
import org.example.restserver.dto.GubunDto;
import org.example.restserver.dto.JobPostDto;
import org.example.restserver.entity.Gubun;
import org.example.restserver.entity.JobPost;
import org.example.restserver.entity.JobPostSkill;
import org.example.restserver.repository.GubunRepository;
import org.example.restserver.repository.JobPostSkillRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<JobPost> getJobPostsBySkillCode(String skillCode) {
        return jobPostSkillRepository.findJobPostsBySkillCode(skillCode);
    }

    public List<JobPost> getJobPostsBySkillCodes(List<String> skillCodes) {
        return jobPostSkillRepository.findJobPostsBySkillCodes(skillCodes);
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
        List<JobPost> jobPosts = getJobPostsBySkillCodes(skillCodes);
        List<JobPostDto> jobPostDtos = new ArrayList<>();

        for (JobPost jobPost : jobPosts) {
            JobPostDto jobPostDto = new JobPostDto();

            jobPostDto.setTitle(jobPost.getTitle());

            if (jobPost.getCompany() != null) {
                jobPostDto.setUsername(jobPost.getCompany().getUsername());
                jobPostDto.setAddress(jobPost.getCompany().getAddress());
            }

            List<String> matchedSkills = new ArrayList<>();
            for (JobPostSkill skill : jobPost.getJobPostSkills()) {
                if (skillCodes.contains(skill.getId().getSkillCode())) {
                    matchedSkills.add(skill.getId().getSkillCode());
                }
            }
            jobPostDto.setJobPostSkills(matchedSkills);

            // 경력
            jobPostDto.setJobHistory(jobPost.getJobHistory());

            jobPostDtos.add(jobPostDto);
        }

        return jobPostDtos;
    }

    public List<JobPostDto> getAllJobPosts() {
        List<JobPost> jobPosts = jobPostSkillRepository.findAllJobPosts();
        return jobPosts.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private JobPostDto convertToDto(JobPost jobPost) {
        JobPostDto dto = new JobPostDto();
        dto.setTitle(jobPost.getTitle());
        dto.setUsername(jobPost.getCompany() != null ? jobPost.getCompany().getUsername() : null);
        dto.setAddress(jobPost.getCompany() != null ? jobPost.getCompany().getAddress() : null);
        dto.setJobHistory(jobPost.getJobHistory());
        dto.setJobPostSkills(
                jobPost.getJobPostSkills().stream()
                        .map(skill -> skill.getId().getSkillCode())
                        .collect(Collectors.toList())
        );
        return dto;
    }
}