package org.example.restserver.service;

import lombok.RequiredArgsConstructor;
import org.example.restserver.dto.JobPostDto;
import org.example.restserver.dto.JobPostResponseDto;
import org.example.restserver.entity.*;
import org.example.restserver.repository.BenefitRepository;
import org.example.restserver.repository.CompanyRepository;
import org.example.restserver.repository.JobPostRepository;
import org.example.restserver.repository.JobPostSkillRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JobPostServiceImpl implements JobPostService {

    private final JobPostRepository jobPostRepository;
    private final BenefitRepository benefitRepository;
    private final JobPostSkillRepository jobPostSkillRepository;
    private final CompanyRepository companyRepository;


    public void register(JobPostDto jobPostDto) {

        Company company = companyRepository.findById(jobPostDto.getUsername()).orElse(null);
        // JobPost 엔티티 생성 - Builder 패턴 사용
        JobPost jobPost = JobPost.builder()
                .company(company)
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


        if (jobPostDto.getJobPostSkills() != null && !jobPostDto.getJobPostSkills().isEmpty()) {
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
        } else {
            System.out.println("No skill codes provided.");
        }
    }


    public List<JobPostResponseDto> getAllJobPostsWithCompany() {
        return jobPostRepository.findActiveJobPostsWithCompanyInfo();
    }


    public JobPostDto getJobPostDetailById(Integer jobpostno) {
        JobPost jobPost = jobPostRepository.findById(jobpostno)
                .orElse(null);
        System.out.println(jobPost+"sdfsdfsdfsdf");


        JobPostDto jobPostDto = new JobPostDto();
        jobPostDto.setUsername(jobPost.getUsername());
        jobPostDto.setTitle(jobPost.getTitle());
        jobPostDto.setWorkCode(jobPost.getWorkCode());
        jobPostDto.setJobHistory(jobPost.getJobHistory());
        jobPostDto.setJobSalary(jobPost.getJobSalary());
        jobPostDto.setEducationCode(jobPost.getEducationCode());
        jobPostDto.setJobRankCode(jobPost.getJobRankCode());
        jobPostDto.setWorkTypeCode(jobPost.getWorkTypeCode());
        jobPostDto.setStartDate(jobPost.getStartDate());
        jobPostDto.setEndDate(jobPost.getEndDate());
        jobPostDto.setContent(jobPost.getContent());
        jobPostDto.setWorkCondition(jobPost.getWorkCondition());
        jobPostDto.setProcess(jobPost.getProcess());
        jobPostDto.setMethod(jobPost.getMethod());
        jobPostDto.setAddNotice(jobPost.getAddNotice());
        jobPostDto.setManagerName(jobPost.getManagerName());
        jobPostDto.setManagerPhone(jobPost.getManagerPhone());
        jobPostDto.setManagerEmail(jobPost.getManagerEmail());
        jobPostDto.setEndYn(jobPost.getEndYn());

        List<String> skillList = new ArrayList<>();
        for (JobPostSkill skill : jobPost.getJobPostSkills()) {
            skillList.add(skill.getId().getSkillCode());  // skillCode를 추가
        }
        jobPostDto.setJobPostSkills(skillList);

        // 베네핏 리스트 처리
        List<String> benefitList = new ArrayList<>();
        for (Benefit benefit : jobPost.getBenefits()) {
            // BenefitId에서 benefitContent 가져오기
            benefitList.add(benefit.getId().getBenefitContent());
        }
        jobPostDto.setBenefitContent(benefitList);
        System.out.println(jobPostDto+ "sdfsdfsdfsdfsdfsdfsd");
        return jobPostDto;
    }
}