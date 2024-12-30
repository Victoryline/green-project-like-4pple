package org.example.restserver.service;

import org.example.restserver.dto.JobPostDto;
import org.example.restserver.entity.*;
import org.example.restserver.repository.BenefitRepository;
import org.example.restserver.repository.JobPostRepository;
import org.example.restserver.repository.JobPostSkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JobPostServiceImpl implements JobPostService {

    private final JobPostRepository jobPostRepository;
    private final BenefitRepository benefitRepository;
    private final JobPostSkillRepository jobPostSkillRepository;

    public JobPostServiceImpl(JobPostRepository jobPostRepository, BenefitRepository benefitRepository, JobPostSkillRepository jobPostSkillRepository) {
        this.jobPostRepository = jobPostRepository;
        this.benefitRepository = benefitRepository;
        this.jobPostSkillRepository = jobPostSkillRepository;
    }

    public void register(JobPostDto jobPostDto) {
        System.out.println("Job Post No: ");  // 로그 추가

        // JobPost 엔티티 생성 - Builder 패턴 사용
        JobPost jobPost = JobPost.builder()
                .username(jobPostDto.getUsername())
                .title(jobPostDto.getTitle())
                .workCode(jobPostDto.getWorkCode())
                .jobHistory(jobPostDto.getJobHistory())
                .jobSalary(jobPostDto.getJobSalary())
                .educationCode(jobPostDto.getEducationCode())
                .jobRankCode(jobPostDto.getJobRankCode())
                .workTypeCode(jobPostDto.getWorkTypeCode())
                .startDate(jobPostDto.getStartDate())
                .endDate(jobPostDto.getEndDate())
                .content(jobPostDto.getContent())
                .workCondition(jobPostDto.getWorkCondition())
                .process(jobPostDto.getProcess())
                .method(jobPostDto.getMethod())
                .addNotice(jobPostDto.getAddNotice())
                .managerName(jobPostDto.getManagerName())
                .managerPhone(jobPostDto.getManagerPhone())
                .managerEmail(jobPostDto.getManagerEmail())
                .endYn('N')  // 기본값은 'N'
                .build();

        // JobPost 저장
        jobPostRepository.save(jobPost);
        int jobPostNo = jobPost.getJobPostNo();


        BenefitId benefitId = new BenefitId();
        benefitId.setBenefitContent(jobPostDto.getContent());
        benefitId.setJobPostNo(jobPostNo);

        Benefit benefit = new Benefit();
        benefit.setId(benefitId);
        benefit.setJobPost(jobPost);

        benefitRepository.save(benefit);


        if(jobPostDto.getJobPostSkills() != null && !jobPostDto.getJobPostSkills().isEmpty()) {
            // skillCode가 null 또는 빈 리스트가 아닐 경우 처리
            for (String skillCodeStr : jobPostDto.getJobPostSkills()) {
                System.out.println(skillCodeStr + "스킬스킬");

                // JobPostSkill 엔티티 생성 및 저장
                JobPostSkillId jobPostSkillId = new JobPostSkillId();
                jobPostSkillId.setJobPostNo(jobPost.getJobPostNo());
                jobPostSkillId.setSkillCode(skillCodeStr);

                JobPostSkill jobPostSkill = new JobPostSkill();
                jobPostSkill.setId(jobPostSkillId);
                jobPostSkill.setJobPost(jobPost);

                // jobPostSkills 리스트가 null인 경우 초기화
                if (jobPost.getJobPostSkills() == null) {
                    jobPost.setJobPostSkills(new ArrayList<>());
                }

                // jobPostSkills 리스트에 추가
                jobPost.getJobPostSkills().add(jobPostSkill);

                // 저장
                jobPostSkillRepository.flush(); // 세션 상태 강제 반영
                //jobPostSkillRepository.clear(); // 세션 초기화 (캐시 초기화)
                jobPostSkillRepository.save(jobPostSkill);
            }
        } else{
            System.out.println("No skill codes provided.");
        }
    }
}