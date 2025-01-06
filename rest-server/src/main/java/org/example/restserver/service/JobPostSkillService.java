package org.example.restserver.service;

import lombok.RequiredArgsConstructor;
import org.example.restserver.dto.GubunDto;
import org.example.restserver.dto.JobPostResponseDto;
import org.example.restserver.dto.JobPostSkillResponseDto;
import org.example.restserver.entity.*;
import org.example.restserver.repository.GubunRepository;
import org.example.restserver.repository.JobPostRepository;
import org.example.restserver.repository.JobPostSkillRepository;
import org.example.restserver.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * packageName    : org.example.restserver.service
 * fileName       : JobPostSkillService
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

    private final JobPostRepository jobPostRepository;
    private final JobPostSkillRepository jobPostSkillRepository;
    private final GubunRepository gubunRepository;
    private final UserRepository userRepository;

    public List<GubunDto> getSkills() {
        return gubunRepository.findAllByIdGubunCode("SKILL").stream()
                .map(skill -> new GubunDto(skill.getId().getGubunCode(), skill.getId().getCode(), skill.getName()))
                .toList();
    }

    public List<JobPostSkillResponseDto> getJobPostsBySkillCode(String skillCode) {
        List<JobPostSkill> jobPostSkills = jobPostSkillRepository.findJobPostSkillsBySkillCode(skillCode);
        return convertToSkillResponseDtoList(jobPostSkills);
    }

    public List<JobPostSkillResponseDto> filterJobPostsBySkills(List<String> skillCodes) {
        List<JobPostSkill> filteredJobPostSkills = jobPostSkillRepository.findJobPostSkillsBySkillCodes(skillCodes);
        return convertToSkillResponseDtoList(filteredJobPostSkills);
    }

    public JobPostSkillResponseDto getDetailById(Integer id) {
        JobPost jobPost = jobPostRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 채용 공고를 찾을 수 없습니다. ID: " + id));

        return new JobPostSkillResponseDto(jobPost);
    }

    public List<JobPostSkillResponseDto> getAllJobPosts() {
        List<JobPostSkill> jobPostSkills = jobPostSkillRepository.findAllJobPosts();
        List<JobPostSkillResponseDto> responseDtos = new ArrayList<>();

        for (JobPostSkill jobPostSkill : jobPostSkills) {
            String companyName = getCompanyName(jobPostSkill);
            responseDtos.add(new JobPostSkillResponseDto(jobPostSkill, companyName));
        }

        return responseDtos;
    }


    private List<JobPostSkillResponseDto> convertToSkillResponseDtoList(List<JobPostSkill> jobPostSkills) {
        List<JobPostSkillResponseDto> responseDtos = new ArrayList<>();

        for (JobPostSkill jobPostSkill : jobPostSkills) {
            String companyName = getCompanyName(jobPostSkill);
            responseDtos.add(new JobPostSkillResponseDto(jobPostSkill, companyName));
        }

        return responseDtos;
    }


    private String getCompanyName(JobPostSkill jobPostSkill) {
        if (jobPostSkill.getJobPost() != null && jobPostSkill.getJobPost().getCompany() != null) {
            String username = jobPostSkill.getJobPost().getCompany().getUsername();
            User user = userRepository.findById(username).orElse(null);
            if (user != null) {
                return user.getName();
            }
        }
        return "회사명 없음";
    }
}