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
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
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
        System.out.println(jobpostno+"sdfsdfsdfsdf");
        JobPost jobPost = jobPostRepository.findById(jobpostno)
                .orElse(null);


        JobPostDto jobPostDto = new JobPostDto();
        jobPostDto.setJobPostNo(jobPost.getJobPostNo());
        jobPostDto.setUsername(jobPost.getCompany().getUsername());
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

    public void modify(JobPostDto jobPostDto) {
        System.out.println(jobPostDto + "수정수정 디티오 디티오 ");

        // username이 null이거나 빈 값인지 확인
        if (jobPostDto.getUsername() == null || jobPostDto.getUsername().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }

        // Company 객체 찾기
        Company company = companyRepository.findById(jobPostDto.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Company not found"));

        // JobPost 엔티티 생성 - Builder 패턴 사용
        JobPost jobPost = new JobPost();

        jobPost.setUsername(jobPostDto.getUsername());
        jobPost.setJobPostNo(jobPostDto.getJobPostNo());
        jobPost.setTitle(jobPostDto.getTitle());
        jobPost.setWorkCode(jobPostDto.getWorkCode());
        jobPost.setJobHistory(jobPostDto.getJobHistory());
        jobPost.setJobSalary(jobPostDto.getJobSalary());
        jobPost.setEducationCode(jobPostDto.getEducationCode());
        jobPost.setJobRankCode(jobPostDto.getJobRankCode());
        jobPost.setWorkTypeCode(jobPostDto.getWorkTypeCode());
        jobPost.setStartDate(jobPostDto.getStartDate());
        jobPost.setEndDate(jobPostDto.getEndDate());
        jobPost.setContent(jobPostDto.getContent());
        jobPost.setWorkCondition(jobPostDto.getWorkCondition());
        jobPost.setProcess(jobPostDto.getProcess());
        jobPost.setMethod(jobPostDto.getMethod());
        jobPost.setAddNotice(jobPostDto.getAddNotice());
        jobPost.setManagerName(jobPostDto.getManagerName());
        jobPost.setManagerPhone(jobPostDto.getManagerPhone());
        jobPost.setManagerEmail(jobPostDto.getManagerEmail());
        jobPost.setEndYn('N');
        jobPost.setCompany(company);

        // JobPost 저장
        jobPostRepository.save(jobPost);
        int jobPostNo = jobPost.getJobPostNo();

        // Benefit 처리
        BenefitId benefitId = new BenefitId();
        benefitId.setBenefitContent(jobPostDto.getContent());
        benefitId.setJobPostNo(jobPostNo);

        Benefit benefit = new Benefit();
        benefit.setId(benefitId);
        benefit.setJobPost(jobPost);

        benefitRepository.save(benefit);

        // JobPostSkills 처리
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
                jobPostSkillRepository.flush();

                jobPostSkillRepository.save(jobPostSkill);
            }
        } else {
            System.out.println("No skill codes provided.");
        }

        System.out.println("수정수정수정 서비스 " + jobPostDto);
    }

    public void delete(Integer jobPostNo) {
        System.out.println("삭제삭wp" + jobPostNo);
        jobPostRepository.deleteById(jobPostNo);
    }
}