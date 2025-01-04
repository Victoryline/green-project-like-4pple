package org.example.restserver.service;

import lombok.RequiredArgsConstructor;
import org.example.restserver.dto.GubunDto;
import org.example.restserver.dto.JobPostResponseDto;
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

    private final JobPostSkillRepository jobPostSkillRepository;
    private final GubunRepository gubunRepository;
    private final UserRepository userRepository;

    public List<JobPostResponseDto> getJobPostsBySkillCode(String skillCode) {
        List<JobPost> jobPosts = jobPostSkillRepository.findJobPostsBySkillCode(skillCode);
        return convertToResponseDtoList(jobPosts);
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

    public List<JobPostResponseDto> getJobPostsBySkills(List<String> skillCodes) {
        List<JobPost> jobPosts;

        if (skillCodes.isEmpty()) {
            jobPosts = jobPostSkillRepository.findAllJobPosts();
        } else {
            jobPosts = jobPostSkillRepository.findJobPostsBySkillCodes(skillCodes);
        }

        return convertToResponseDtoList(jobPosts);
    }

    public List<JobPostResponseDto> getAllJobPosts() {
        List<JobPost> jobPosts = jobPostSkillRepository.findAllJobPosts();
        return convertToResponseDtoList(jobPosts);
    }

    private List<JobPostResponseDto> convertToResponseDtoList(List<JobPost> jobPosts) {
        List<JobPostResponseDto> responseDtos = new ArrayList<>();

        for (JobPost jobPost : jobPosts) {
            // User에서 이름 가져오기
            String name = "회사명 없음";
            if (jobPost.getCompany() != null && jobPost.getCompany().getUsername() != null) {
                User user = userRepository.findById(jobPost.getCompany().getUsername()).orElse(null);
                if (user != null) {
                    name = user.getName();
                }
            }

            // Company에서 주소 가져오기
            String address = jobPost.getCompany() != null ? jobPost.getCompany().getAddress() : "주소 없음";

            // 기술 스택 처리
            List<String> skillList = new ArrayList<>();
            for (JobPostSkill skill : jobPost.getJobPostSkills()) {
                skillList.add(skill.getId().getSkillCode());
            }

            // 프로필 이미지 처리
            byte[] profileImageBytes = null;
            if (jobPost.getCompany() != null && jobPost.getCompany().getProfileImage() != null) {
                profileImageBytes = jobPost.getCompany().getProfileImage();
            }

            // JobPostResponseDto 생성 및 추가
            responseDtos.add(new JobPostResponseDto(
                    jobPost.getJobPostNo(),
                    jobPost.getCompany() != null ? jobPost.getCompany().getUsername() : null,
                    name,
                    jobPost.getTitle(),
                    jobPost.getWorkCode(),
                    jobPost.getJobHistory(),
                    jobPost.getJobSalary(),
                    jobPost.getStartDate(),
                    jobPost.getEndDate(),
                    jobPost.getWorkCondition(),
                    jobPost.getEndYn(),
                    String.join(", ", skillList), // 기술 스택을 쉼표로 연결
                    address,
                    profileImageBytes
            ));
        }

        return responseDtos;
    }
}